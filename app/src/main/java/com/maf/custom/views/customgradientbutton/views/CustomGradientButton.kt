package com.maf.custom.views.customgradientbutton.views

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.withStyledAttributes
import com.maf.custom.views.customgradientbutton.R

class CustomGradientButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AbstractComposeView(context, attrs, defStyleAttr) {

    var buttonText by mutableStateOf("")
    var textColor by mutableStateOf(0x000)
    var topBoarderColor by mutableStateOf(0xffffff)
    var bottomBoarderColor by mutableStateOf(0xffffff)
    var buttonBackground by mutableStateOf(0xfff)
    var borderRound by mutableStateOf(0.dp)
    var borderWidth by mutableStateOf(0.dp)
    var innerVerticalPadding by mutableStateOf(0.dp)
    var innerHorizontalPadding by mutableStateOf(0.dp)
    var buttonTextSize by mutableStateOf(14.sp)
    var fontWidth by mutableStateOf(400)
    var iconsArrangement by mutableStateOf(0)
    var startIconRes: Int? by mutableStateOf(null)
    var startIconSize by mutableStateOf(25.dp)
    var startIconPadding by mutableStateOf(8.dp)
    var endIconRes: Int? by mutableStateOf(null)
    var endIconSize by mutableStateOf(25.dp)
    var endIconPadding by mutableStateOf(8.dp)
    var buttonEnabled by mutableStateOf(true)
    var onClick: (() -> Unit)? = null

    init {
        context.withStyledAttributes(attrs, R.styleable.CustomButton) {
            buttonText = getString(R.styleable.CustomButton_textTitle).toString()
            textColor = getColor(R.styleable.CustomButton_textColor, 0)
            topBoarderColor = getColor(R.styleable.CustomButton_topBoarderColor, 0)
            bottomBoarderColor = getColor(R.styleable.CustomButton_bottomBoarderColor, 0)
            buttonBackground = getColor(R.styleable.CustomButton_buttonBackground, 0)
            borderRound = getInt(R.styleable.CustomButton_roundedBoarder, 0).dp
            borderWidth = getInt(R.styleable.CustomButton_buttonBorderWidth, 0).dp
            innerVerticalPadding = getInt(R.styleable.CustomButton_innerVerticalPadding, 10).dp
            innerHorizontalPadding = getInt(R.styleable.CustomButton_innerHorizontalPadding, 20).dp
            buttonTextSize = getInt(R.styleable.CustomButton_buttonTextSize, 14).sp
            buttonEnabled = getBoolean(R.styleable.CustomButton_isEnabled, true)
            fontWidth = getInteger(R.styleable.CustomButton_fontWidth, 400)
            iconsArrangement = getInteger(R.styleable.CustomButton_iconsArrangement, 0)
            startIconRes = getResourceId(R.styleable.CustomButton_startIconRes, 0)
            startIconSize = getInt(R.styleable.CustomButton_startIconSize, 25).dp
            startIconPadding = getInt(R.styleable.CustomButton_startIconPadding, 8).dp
            endIconRes = getResourceId(R.styleable.CustomButton_endIconRes, 0)
            endIconSize = getInt(R.styleable.CustomButton_endIconSize, 25).dp
            endIconPadding = getInt(R.styleable.CustomButton_endIconPadding, 8).dp
        }
    }

    override fun onAttachedToWindow() {
        setParentCompositionContext(null)
        super.onAttachedToWindow()
    }


    @Composable
    override fun Content() {
        GradientButton(
            buttonText = buttonText,
            buttonTextColor = Color(textColor),
            topBoarderColor = Color(topBoarderColor),
            bottomBoarderColor = Color(bottomBoarderColor),
            buttonBackground = Color(buttonBackground),
            borderRound = borderRound,
            borderWidth = borderWidth,
            innerVerticalPadding = innerVerticalPadding,
            innerHorizontalPadding = innerHorizontalPadding,
            buttonTextSize = buttonTextSize,
            buttonEnabled = buttonEnabled,
            fontWeight = fontWidth,
            iconsArrangement = iconsArrangement,
            startIconRes = if (startIconRes == 0) null else startIconRes,
            endIconRes = if (endIconRes == 0) null else endIconRes,
            startIconSize = startIconSize,
            endIconSize = endIconSize,
            startIconPadding = startIconPadding,
            endIconPadding = endIconPadding,
            onClick = onClick ?: { }
        )
    }
}

@Composable
fun GradientButton(
    borderRound: Dp = 0.dp,
    buttonBackground: Color = Color.Red,
    buttonEnabled: Boolean = true,
    borderWidth: Dp = 1.dp,
    topBoarderColor: Color = Color.Black,
    bottomBoarderColor: Color = Color.Green,
    innerVerticalPadding: Dp = 10.dp,
    innerHorizontalPadding: Dp = 20.dp,
    buttonText: String = "Preview",
    buttonTextColor: Color = Color.Black,
    buttonTextSize: TextUnit = 20.sp,
    fontWeight: Int = 400,
    @DrawableRes startIconRes: Int? = null,
    @DrawableRes endIconRes: Int? = null,
    iconsArrangement: Int = 0,
    startIconSize: Dp = 25.dp,
    startIconPadding: Dp = 8.dp,
    endIconSize: Dp = 25.dp,
    endIconPadding: Dp = 8.dp,
    onClick: () -> Unit = { },
) {
    var arrangement = when (iconsArrangement) {
        0 -> Arrangement.Center
        1 -> Arrangement.SpaceBetween
        else -> Arrangement.Start
    }

    if (endIconRes == null || startIconRes == null) {
        arrangement = Arrangement.Center
    }

    val enabledModifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(RoundedCornerShape(borderRound))
        .background(buttonBackground)
        .clickable(
            enabled = buttonEnabled,
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = false, 400.dp),
            onClick = onClick
        )
        .border(
            borderWidth, Brush.verticalGradient(
                colors = listOf(
                    topBoarderColor,
                    bottomBoarderColor
                ),
            ), RoundedCornerShape(borderRound)
        )
        .clipToBounds()
        .padding(bottom = innerVerticalPadding, top = innerVerticalPadding)
        .alpha(if (buttonEnabled) 1f else 0.3f)

    Box(
        modifier = enabledModifier
    ) {
        Row(
            verticalAlignment = CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .align(Center)
                .padding(start = innerHorizontalPadding, end = innerHorizontalPadding),
            horizontalArrangement = arrangement
        ) {
            startIconRes?.let {
                Image(
                    painter = painterResource(id = startIconRes),
                    contentDescription = null,
                    modifier = Modifier.size(startIconSize),
                    colorFilter = ColorFilter.tint(buttonTextColor)
                )
                Spacer(modifier = Modifier.width(startIconPadding))
            }
            Text(
                modifier = Modifier
                    .padding(0.dp),
                text = buttonText,
                style = TextStyle(
                    color = if (buttonEnabled) buttonTextColor else Color.Black,
                    fontSize = buttonTextSize,
                    fontWeight = FontWeight(fontWeight),
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false,
                    )
                )
            )
            endIconRes?.let {
                Spacer(modifier = Modifier.width(endIconPadding))
                Image(
                    painter = painterResource(id = endIconRes),
                    contentDescription = null,
                    modifier = Modifier.size(endIconSize),
                    colorFilter = ColorFilter.tint(buttonTextColor)
                )
            }
        }

    }
}

@Preview
@Composable
fun PreviewGradientButton() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            GradientButton(
                borderRound = 100.dp,
                borderWidth = 4.dp,
                buttonText = "TestOne",
                buttonBackground = Color.White,
                topBoarderColor = Color.Magenta,
                bottomBoarderColor = Color.Red,
                startIconRes = R.drawable.ic_launcher_background

            )
            Spacer(modifier = Modifier.height(20.dp))
            GradientButton(
                borderRound = 100.dp,
                borderWidth = 0.dp,
                buttonText = "TestTwo",
                fontWeight = 600,
                buttonEnabled = true,
                buttonTextColor = Color.White,
                startIconRes = R.drawable.ic_launcher_background,
                endIconRes = R.drawable.ic_launcher_background,
                startIconSize = 50.dp,
                iconsArrangement = 1
            )
            Spacer(modifier = Modifier.height(20.dp))
            GradientButton(
                borderRound = 100.dp,
                borderWidth = 0.dp,
                buttonText = "TestThree",
                fontWeight = 600,
                buttonEnabled = false,
                buttonBackground = Color(0xFF4E8DBE),
                buttonTextColor = Color.White,
            )
            Spacer(modifier = Modifier.height(20.dp))
            GradientButton(
                borderRound = 10.dp,
                borderWidth = 4.dp,
                iconsArrangement = 1,
                innerVerticalPadding = 30.dp,
                buttonText = "TestFour",
                fontWeight = 600,
                buttonEnabled = true,
                buttonBackground = Color.White,
                buttonTextColor = Color.Red,
                endIconRes = R.drawable.ic_launcher_background
            )
        }

    }

}