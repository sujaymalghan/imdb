package com.example.moviedetails.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val LocalMyColorScheme = compositionLocalOf { MyLightPinkTheme.colorScheme }
val LocalMyTypography = compositionLocalOf { MyLightPinkTheme.typography }

@Composable
fun MyLightPinkTheme(
    colorScheme: ColorScheme = MyLightPinkTheme.colorScheme,
    typography: Typography = MyLightPinkTheme.typography,
    content: @Composable () -> Unit
) {
    val rememberedColorScheme = remember { colorScheme.copy() }

    CompositionLocalProvider(
        LocalMyColorScheme provides rememberedColorScheme,
        LocalMyTypography provides typography
    ) {
        content()
    }
}

object MyLightPinkTheme {
    val colorScheme: ColorScheme = ColorScheme(
        primary = Color(0xFFFFFFFF), // light pink
        onPrimary = Color(0xFF000000), // black
        primaryContainer = Color(0xFFE6ABB2),
        onPrimaryContainer = Color(0xFF000000),
        inversePrimary = Color(0xFFFFFFFF),
        secondary = Color(0xFFFCF3CF), // light yellow
        onSecondary = Color(0xFF000000),
        secondaryContainer = Color(0xFFEAE1C7),
        onSecondaryContainer = Color(0xFF000000),
        tertiary = Color(0xFFCCFFFF),
        onTertiary = Color(0xFF000000),
        tertiaryContainer = Color(0xFF99FFFF),
        onTertiaryContainer = Color(0xFF000000),
        background = Color(0xFFFFFFFF), // white
        onBackground = Color(0xFF000000),
        surface = Color(0xFFFFFFFF),
        onSurface = Color(0xFF000000),
        surfaceVariant = Color(0xFFEEEEEE),
        onSurfaceVariant = Color(0xFF000000),
        surfaceTint = Color(0xFFF0F0F0),
        inverseSurface = Color(0xFF000000),
        inverseOnSurface = Color(0xFFFFFFFF),
        error = Color(0xFFB00020),
        onError = Color(0xFFFFFFFF),
        errorContainer = Color(0xFF9E0000),
        onErrorContainer = Color(0xFFFFFFFF),
        outline = Color(0xFFD1D1D1),
        outlineVariant = Color(0xFFB0B0B0),
        scrim = Color(0x99000000) // 60% opaque black
    )

    val typography: Typography = Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        )
    )
}
