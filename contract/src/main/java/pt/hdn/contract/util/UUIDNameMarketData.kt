package pt.hdn.contract.util

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.annotations.Market
import java.util.*

@Parcelize
data class UUIDNameMarketData(
    @Expose val uuid: UUID = UUID.randomUUID(),
    @Expose val name: String = "",
    @Expose @Market val market: Int = Market.NONE
) : Parcelable {
    override fun toString(): String = name

    fun getSpinnerHeader(text: String) = UUIDNameMarketData(name = text)
}
