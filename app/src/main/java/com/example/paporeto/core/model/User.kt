package com.example.paporeto.core.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.example.paporeto.R
import kotlinx.android.parcel.Parcelize
import java.util.UUID

@kotlinx.parcelize.Parcelize
data class User(
    val id: UUID = UUID.randomUUID(),
    val fullName: String,
    val email: String,
    @DrawableRes val profileImageUrl: Int?
): Parcelable {
    companion object {
        val mock = User(
            fullName = "Cristiano Ronaldo",
            email = "cris@email.com",
            profileImageUrl = R.drawable.cris_teste
        )
    }
}
