package com.narrowstudio.sisenor.word.domain

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object BooleanAsIntSerializer : KSerializer<Boolean> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("BooleanAsInt", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: Boolean) {
        encoder.encodeInt(if (value) 1 else 0)
    }

    override fun deserialize(decoder: Decoder): Boolean {
        return decoder.decodeInt() != 0
    }
}

object NullableBooleanAsIntSerializer : KSerializer<Boolean?> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("NullableBooleanAsInt", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: Boolean?) {
        encoder.encodeInt(value?.let { if (it) 1 else 0 } ?: 0)
    }

    override fun deserialize(decoder: Decoder): Boolean? {
        return when (val intValue = decoder.decodeInt()) {
            1 -> true
            0 -> false
            else -> null
        }
    }
}
