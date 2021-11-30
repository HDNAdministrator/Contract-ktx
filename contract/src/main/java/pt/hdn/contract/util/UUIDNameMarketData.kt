package pt.hdn.contract.util

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.annotations.MarketType

@Parcelize
data class UUIDNameMarketData(
    @Expose val uuid: String = "",
    @Expose val name: String = "",
    @Expose @MarketType val marketType: Int = MarketType.NONE
) : Parcelable {
    override fun toString(): String = name

    fun getSpinnerHeader(text: String) = UUIDNameMarketData(
            name = text
    )
}
