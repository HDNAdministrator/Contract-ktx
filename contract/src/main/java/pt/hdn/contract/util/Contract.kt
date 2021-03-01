package pt.hdn.contract.util

import android.os.Parcelable
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.adapters.ByteArrayTypeAdapter
import pt.hdn.contract.adapters.SchemaTypeAdapter
import pt.hdn.contract.adapters.ZonedDateTimeTypeAdapter
import pt.hdn.contract.schemas.Schema
import java.time.ZonedDateTime

@Parcelize
data class Contract(
    @Expose var tasks: MutableList<Task>,
    @Expose var recurrence: Recurrence,
    @Expose var buyerId: String,
    @Expose var buyerDeputyId: String,
    @Expose var sellerId: String,
    @Expose var sellerDeputyId: String,
    @Expose var witnessId: String,
    @Expose var buyerTimestamp: ZonedDateTime,
    @Expose var buyerDeputyTimestamp: ZonedDateTime,
    @Expose var sellerTimestamp: ZonedDateTime,
    @Expose var sellerDeputyTimestamp: ZonedDateTime,
    @Expose var witnessTimestamp: ZonedDateTime,
    @Expose var buyerSignature: ByteArray? = null,
    @Expose var buyerDeputySignature: ByteArray? = null,
    @Expose var sellerSignature: ByteArray? = null,
    @Expose var sellerDeputySignature: ByteArray? = null,
    @Expose var witnessSignature: ByteArray? = null
) : Parcelable {

    companion object {
        val gsonBuilder: GsonBuilder by lazy { GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(ByteArray::class.java, ByteArrayTypeAdapter()).registerTypeAdapter(Schema::class.java, SchemaTypeAdapter()).registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeTypeAdapter()) }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Contract) return false

        if (tasks != other.tasks) return false
        if (recurrence != other.recurrence) return false
        if (buyerId != other.buyerId) return false
        if (buyerDeputyId != other.buyerDeputyId) return false
        if (sellerId != other.sellerId) return false
        if (sellerDeputyId != other.sellerDeputyId) return false
        if (witnessId != other.witnessId) return false
        if (buyerTimestamp != other.buyerTimestamp) return false
        if (buyerDeputyTimestamp != other.buyerDeputyTimestamp) return false
        if (sellerTimestamp != other.sellerTimestamp) return false
        if (sellerDeputyTimestamp != other.sellerDeputyTimestamp) return false
        if (witnessTimestamp != other.witnessTimestamp) return false
        if (buyerSignature != null) {
            if (other.buyerSignature == null) return false
            if (!buyerSignature.contentEquals(other.buyerSignature)) return false
        } else if (other.buyerSignature != null) return false
        if (buyerDeputySignature != null) {
            if (other.buyerDeputySignature == null) return false
            if (!buyerDeputySignature.contentEquals(other.buyerDeputySignature)) return false
        } else if (other.buyerDeputySignature != null) return false
        if (sellerSignature != null) {
            if (other.sellerSignature == null) return false
            if (!sellerSignature.contentEquals(other.sellerSignature)) return false
        } else if (other.sellerSignature != null) return false
        if (sellerDeputySignature != null) {
            if (other.sellerDeputySignature == null) return false
            if (!sellerDeputySignature.contentEquals(other.sellerDeputySignature)) return false
        } else if (other.sellerDeputySignature != null) return false
        if (witnessSignature != null) {
            if (other.witnessSignature == null) return false
            if (!witnessSignature.contentEquals(other.witnessSignature)) return false
        } else if (other.witnessSignature != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = tasks.hashCode()
        result = 31 * result + recurrence.hashCode()
        result = 31 * result + buyerId.hashCode()
        result = 31 * result + buyerDeputyId.hashCode()
        result = 31 * result + sellerId.hashCode()
        result = 31 * result + sellerDeputyId.hashCode()
        result = 31 * result + witnessId.hashCode()
        result = 31 * result + buyerTimestamp.hashCode()
        result = 31 * result + buyerDeputyTimestamp.hashCode()
        result = 31 * result + sellerTimestamp.hashCode()
        result = 31 * result + sellerDeputyTimestamp.hashCode()
        result = 31 * result + witnessTimestamp.hashCode()
        result = 31 * result + (buyerSignature?.contentHashCode() ?: 0)
        result = 31 * result + (buyerDeputySignature?.contentHashCode() ?: 0)
        result = 31 * result + (sellerSignature?.contentHashCode() ?: 0)
        result = 31 * result + (sellerDeputySignature?.contentHashCode() ?: 0)
        result = 31 * result + (witnessSignature?.contentHashCode() ?: 0)
        return result
    }

}
