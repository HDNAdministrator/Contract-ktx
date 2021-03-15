package pt.hdn.contract.schemas

import android.os.Parcelable
import pt.hdn.contract.annotations.SchemaType
import pt.hdn.contract.annotations.SourceType

interface Schema: Parcelable {
    @SourceType var source: Int?
    @SchemaType val id: Int
    val isValid: Boolean
}