package pt.hdn.contract.annotations

import androidx.annotation.StringDef

@Retention(AnnotationRetention.SOURCE)
@StringDef(Field.BUYER, Field.SELLER, Field.WITNESS, Field.BUYER_TIMESTAMP, Field.BUYER_DEPUTY_TIMESTAMP, Field.SELLER, Field.SELLER_DEPUTY_TIMESTAMP, Field.SELLER_TIMESTAMP, Field.WITNESS_TIMESTAMP)
annotation class Field {
    companion object {
        const val BUYER: String = "buyer"
        const val BUYER_ID: String = "buyerId"
        const val BUYER_TIMESTAMP: String = "buyerTimestamp"
        const val BUYER_DEPUTY: String = "buyerDeputy"
        const val BUYER_DEPUTY_ID: String = "buyerDeputyId"
        const val BUYER_DEPUTY_TIMESTAMP: String = "buyerDeputyTimestamp"
        const val SELLER: String = "seller"
        const val SELLER_ID: String = "sellerId"
        const val SELLER_TIMESTAMP: String = "sellerTimestamp"
        const val SELLER_DEPUTY: String = "sellerDeputy"
        const val SELLER_DEPUTY_ID: String = "sellerDeputyId"
        const val SELLER_DEPUTY_TIMESTAMP: String = "sellerDeputyTimestamp"
        const val WITNESS: String = "witness"
        const val WITNESS_ID: String = "witnessId"
        const val WITNESS_TIMESTAMP: String = "witnessTimestamp"
    }
}
