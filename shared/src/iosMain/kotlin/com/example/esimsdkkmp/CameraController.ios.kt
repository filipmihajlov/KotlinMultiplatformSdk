package com.example.esimsdkkmp

import com.example.esimsdkkmp.domain.model.BusinessCardDraft

actual class CameraController actual constructor() {

    companion object {
        /**
         * Hook that the iOS facade (or host app later) can override.
         * Default is a simple fake implementation.
         */
        var launcher: suspend () -> BusinessCardDraft? = {
            BusinessCardDraft(
                fullName = "Filip (iOS fake)",
                company = "Demo Co",
                email = "filip+ios@example.com",
                phone = null,
                website = null,
                notes = "Created by iOS CameraController",
                imageRef = null,
            )
        }
    }

    actual suspend fun captureDraft(): BusinessCardDraft? = launcher()
}
