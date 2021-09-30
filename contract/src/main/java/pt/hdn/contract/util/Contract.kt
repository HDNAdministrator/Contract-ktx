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
    val hasBuyerSigned: Boolean; get() = buyerSignature != null && buyerDeputySignature != null
    val hasSellerSigned: Boolean; get() = sellerSignature != null && sellerDeputySignature != null
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
        if (uuid != other.uuid) return false

        return true
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
}
