package pt.hdn.contract.util

import android.os.Parcelable
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.adapters.ByteArrayTypeAdapter
import pt.hdn.contract.adapters.SchemaTypeAdapter
import pt.hdn.contract.adapters.ZonedDateTimeTypeAdapter
import pt.hdn.contract.annotations.Err
import pt.hdn.contract.schemas.Schema
import java.time.ZonedDateTime

@Parcelize
data class Contract(
    @Expose val tasks: MutableList<Task>,
    @Expose val recurrence: Recurrence,
    @Expose val buyerId: String,
    @Expose val buyerDeputyId: String,
    @Expose val sellerId: String,
    @Expose val sellerDeputyId: String,
    @Expose val witnessId: String? = null,
    @Expose val buyerTimestamp: ZonedDateTime? = null,
    @Expose val buyerDeputyTimestamp: ZonedDateTime? = null,
    @Expose val sellerTimestamp: ZonedDateTime? = null,
    @Expose val sellerDeputyTimestamp: ZonedDateTime? = null,
    @Expose val witnessTimestamp: ZonedDateTime? = null,
    @Expose val buyerSignature: ByteArray? = null,
    @Expose val buyerDeputySignature: ByteArray? = null,
    @Expose val sellerSignature: ByteArray? = null,
    @Expose val sellerDeputySignature: ByteArray? = null,
    @Expose val witnessSignature: ByteArray? = null,
    @Expose val uuid: String? = null
) : Parcelable, Cloneable {

    //region vars
    @IgnoredOnParcel val hasBuyerSigned: Boolean; get() = buyerSignature != null && buyerDeputySignature != null
    @IgnoredOnParcel val hasSellerSigned: Boolean; get() = sellerSignature != null && sellerDeputySignature != null
    //endregion vars

    companion object {
        val gsonBuilder: GsonBuilder by lazy { GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter(ByteArray::class.java, ByteArrayTypeAdapter())
            .registerTypeAdapter(Schema::class.java, SchemaTypeAdapter())
            .registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeTypeAdapter())
        }

        fun from(json: String): Contract = gsonBuilder.create().fromJson(json, Contract::class.java)
    }

    public override fun clone(): Contract {
        return copy(
            tasks = tasks.mapTo(mutableListOf()) { it.clone() },
            recurrence = recurrence.copy(),
            buyerId = buyerId,
            buyerDeputyId = buyerDeputyId,
            sellerId = sellerId,
            sellerDeputyId = sellerDeputyId,
            witnessId = witnessId,
            buyerTimestamp = null,
            buyerDeputyTimestamp = null,
            sellerTimestamp = null,
            sellerDeputyTimestamp = null,
            witnessTimestamp = null,
            buyerSignature = null,
            buyerDeputySignature = null,
            sellerSignature = null,
            sellerDeputySignature = null,
            witnessSignature = null,
            uuid = null
        )
    }

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other !is Contract -> false
            tasks != other.tasks -> false
            recurrence != other.recurrence -> false
            buyerId != other.buyerId -> false
            sellerId != other.sellerId -> false
            else -> true
        }
    }

    override fun hashCode(): Int {
        var result = tasks.hashCode()
        result = 31 * result + recurrence.hashCode()
        result = 31 * result + buyerId.hashCode()
        result = 31 * result + buyerDeputyId.hashCode()
        result = 31 * result + sellerId.hashCode()
        result = 31 * result + sellerDeputyId.hashCode()
        result = 31 * result + (witnessId?.hashCode() ?: 0)
        result = 31 * result + (buyerTimestamp?.hashCode() ?: 0)
        result = 31 * result + (buyerDeputyTimestamp?.hashCode() ?: 0)
        result = 31 * result + (sellerTimestamp?.hashCode() ?: 0)
        result = 31 * result + (sellerDeputyTimestamp?.hashCode() ?: 0)
        result = 31 * result + (witnessTimestamp?.hashCode() ?: 0)
        result = 31 * result + (buyerSignature?.contentHashCode() ?: 0)
        result = 31 * result + (buyerDeputySignature?.contentHashCode() ?: 0)
        result = 31 * result + (sellerSignature?.contentHashCode() ?: 0)
        result = 31 * result + (sellerDeputySignature?.contentHashCode() ?: 0)
        result = 31 * result + (witnessSignature?.contentHashCode() ?: 0)
        result = 31 * result + (uuid?.hashCode() ?: 0)
        return result
    }

    fun toJson(): String = gsonBuilder.create().toJson(this)

    @Err fun validate(contract: Contract? = null): Int {
        var err = Err.NONE

        return when {
            contract?.let { this == it } == true -> Err.NO_CHANGE
            tasks.isEmpty() -> Err.TASKS
            tasks.all { it.validate().also { err = it } != Err.NONE } || recurrence.validate().also { err = it } != Err.NONE -> err
            else -> Err.NONE
        }
    }
}
