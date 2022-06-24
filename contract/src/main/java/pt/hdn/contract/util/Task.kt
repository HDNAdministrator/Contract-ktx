package pt.hdn.contract.util

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.annotations.Err
import pt.hdn.contract.annotations.SchemaType.Companion.FIX
import pt.hdn.contract.annotations.SchemaType.Companion.RATE
import pt.hdn.contract.annotations.SchemaType.Companion.COMMISSION
import pt.hdn.contract.annotations.SchemaType.Companion.OBJECTIVE
import pt.hdn.contract.annotations.SchemaType.Companion.THRESHOLD
import pt.hdn.contract.schemas.CommissionSchema
import pt.hdn.contract.schemas.ObjectiveSchema
import pt.hdn.contract.schemas.Schema
import pt.hdn.contract.schemas.ThresholdSchema
import java.math.BigDecimal.ZERO

@Parcelize
data class Task(
    @Expose var specialityType: UUIDNameMarketData,
    @Expose var schemas: MutableList<Schema> = mutableListOf(),
    @Expose var responsibilities: List<UUIDNameData>? = null,
    @Expose var exclusivity: Boolean? = null
) : Parcelable, Cloneable {

    public override fun clone(): Task {
        return copy(
            schemas = schemas.mapTo(mutableListOf()) { it.clone() },
            responsibilities = responsibilities?.mapTo(mutableListOf()) { it }
        )
    }

    @Err fun validate(task: Task? = null): Int {
        var err = Err.NONE

        return when {
            this == task -> Err.NO_CHANGE
            schemas.isEmpty() -> Err.SCHEMAS
            schemas.any { it.validate().also { err = it } != Err.NONE } -> err
            schemas
                .groupBy { it.id }
                .any {
                    when (it.key) {
                        FIX -> { if (it.value.size > 1) { err = Err.MORE_THAN_ONE; true } else false }
                        RATE -> { when {it.value.size > 2 -> { err = Err.MORE_THAN_ONE; true }; it.value.size == 2 && it.value.first().source == it.value.last().source -> { err = Err.OVERLAP; true }; else -> false } }
                        COMMISSION -> {
                            it.value
                                .groupBy { it.source!! }
                                .any {
                                    if (it.value.size > 1) {
                                        it.value
                                            .asSequence()
                                            .map { it as CommissionSchema }
                                            .sortedWith(compareBy<CommissionSchema> { it.lowerBound }.thenBy { it.upperBound })
                                            .zipWithNext()
                                            .any { if ((it.second.lowerBound ?: ZERO) < it.first.upperBound) { err = Err.OVERLAP; true } else false }
                                    }
                                    else false
                                }
                        }
                        OBJECTIVE -> {
                            it.value
                                .groupBy { it.source!! }
                                .any {
                                    if (it.value.size > 1) {
                                        it.value
                                            .asSequence()
                                            .map { it as ObjectiveSchema }
                                            .sortedWith(compareBy<ObjectiveSchema> { it.lowerBound }.thenBy { it.upperBound })
                                            .zipWithNext()
                                            .any { if ((it.second.lowerBound ?: ZERO) < it.first.upperBound) { err = Err.OVERLAP; true } else false }
                                    }
                                    else false
                                }
                        }
                        THRESHOLD -> {
                            it.value
                                .groupBy { it.source!! }
                                .any {
                                    if (it.value.size > 1) {
                                        it.value
                                            .asSequence()
                                            .map { it as ThresholdSchema }
                                            .sortedWith(compareBy<ThresholdSchema> { it.threshold }.thenBy { it.isAbove })
                                            .zipWithNext()
                                            .any { if (it.first.threshold!! == it.second.threshold!! && it.first.isAbove!! == it.second.isAbove!!) { err = Err.OVERLAP; true } else false }
                                    }
                                    else false
                                }
                        }
                        else -> false
                    }
                } -> err
            responsibilities?.isEmpty()  == true -> Err.RESPONSIBILITIES
            else -> Err.NONE
        }
    }
}