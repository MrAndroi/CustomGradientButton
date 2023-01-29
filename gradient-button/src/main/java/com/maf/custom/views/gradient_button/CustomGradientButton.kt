package com.maf.custom.views.gradient_button

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.withStyledAttributes

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
    var disabledButtonBackground by mutableStateOf(0x6F6F6F)
    var disabledButtonBackgroundAlpha by mutableStateOf(1f)
    var disabledTextAlpha by mutableStateOf(1f)
    var borderRound by mutableStateOf(0)
    var borderWidth by mutableStateOf(0)
    var innerVerticalPadding by mutableStateOf(0)
    var innerHorizontalPadding by mutableStateOf(0)
    var buttonTextSize by mutableStateOf(14)
    var fontWidth by mutableStateOf(400)
    var iconsArrangement by mutableStateOf(0)
    var startIconRes: Int? by mutableStateOf(null)
    var startIconSize by mutableStateOf(25)
    var startIconPadding by mutableStateOf(8)
    var endIconRes: Int? by mutableStateOf(null)
    var endIconSize by mutableStateOf(25)
    var endIconPadding by mutableStateOf(8)
    var buttonEnabled by mutableStateOf(true)
    var animatedBoarder by mutableStateOf(false)
    var animationSpeed by mutableStateOf(2000)
    var clickEffectColor by mutableStateOf(0xffffff)
    var fontFamily by mutableStateOf(0)
    private var onClick: (() -> Unit)? = null

    init {
        context.withStyledAttributes(attrs, R.styleable.CustomButton) {
            buttonText = getString(R.styleable.CustomButton_textTitle).toString()
            textColor = getColor(R.styleable.CustomButton_textColor, 0)
            topBoarderColor = getColor(R.styleable.CustomButton_topBoarderColor, 0)
            bottomBoarderColor = getColor(R.styleable.CustomButton_bottomBoarderColor, 0)
            buttonBackground = getColor(R.styleable.CustomButton_buttonBackground, 0)
            disabledButtonBackground =
                getColor(R.styleable.CustomButton_disabledButtonBackground, 0x6F6F6F)
            disabledButtonBackgroundAlpha =
                getFloat(R.styleable.CustomButton_disabledButtonBackgroundAlpha, 1f)
            disabledTextAlpha = getFloat(R.styleable.CustomButton_disabledTextAlpha, 1f)
            borderRound = getInt(R.styleable.CustomButton_roundedBoarder, 0)
            borderWidth = getInt(R.styleable.CustomButton_buttonBorderWidth, 0)
            innerVerticalPadding = getInt(R.styleable.CustomButton_innerVerticalPadding, 10)
            innerHorizontalPadding = getInt(R.styleable.CustomButton_innerHorizontalPadding, 20)
            buttonTextSize = getInt(R.styleable.CustomButton_buttonTextSize, 14)
            buttonEnabled = getBoolean(R.styleable.CustomButton_isEnabled, true)
            animatedBoarder = getBoolean(R.styleable.CustomButton_animatedBoarder, false)
            animationSpeed = getInteger(R.styleable.CustomButton_animationSpeed, 2000)
            fontWidth = getInteger(R.styleable.CustomButton_fontWidth, 400)
            iconsArrangement = getInteger(R.styleable.CustomButton_iconsArrangement, 0)
            startIconRes = getResourceId(R.styleable.CustomButton_startIconRes, 0)
            startIconSize = getInt(R.styleable.CustomButton_startIconSize, 25)
            startIconPadding = getInt(R.styleable.CustomButton_startIconPadding, 8)
            endIconRes = getResourceId(R.styleable.CustomButton_endIconRes, 0)
            endIconSize = getInt(R.styleable.CustomButton_endIconSize, 25)
            endIconPadding = getInt(R.styleable.CustomButton_endIconPadding, 8)
            clickEffectColor = getColor(R.styleable.CustomButton_clickEffectColor, 0xffffff)
            fontFamily = getResourceId(R.styleable.CustomButton_font, 0)
        }
    }

    override fun onAttachedToWindow() {
        if (isInEditMode) {
            setupEditMode()
        }
        super.onAttachedToWindow()
    }


    @Composable
    override fun Content() {

        val buttonText = rememberSaveable(this.buttonText) { mutableStateOf(this.buttonText) }
        val textColor = rememberSaveable(this.textColor) { mutableStateOf(this.textColor) }
        val topBoarderColor =
            rememberSaveable(this.topBoarderColor) { mutableStateOf(this.topBoarderColor) }
        val bottomBoarderColor =
            rememberSaveable(this.bottomBoarderColor) { mutableStateOf(this.bottomBoarderColor) }
        val buttonBackground =
            rememberSaveable(this.buttonBackground) { mutableStateOf(this.buttonBackground) }
        val disabledButtonBackground =
            rememberSaveable(this.disabledButtonBackground) { mutableStateOf(this.disabledButtonBackground) }
        val disabledButtonBackgroundAlpha =
            rememberSaveable(this.disabledButtonBackgroundAlpha) { mutableStateOf(this.disabledButtonBackgroundAlpha) }
        val disabledTextAlpha =
            rememberSaveable(this.disabledTextAlpha) { mutableStateOf(this.disabledTextAlpha) }
        val borderRound =
            rememberSaveable(this.borderRound) { mutableStateOf(this.borderRound) }
        val borderWidth =
            rememberSaveable(this.borderWidth) { mutableStateOf(this.borderWidth) }
        val innerVerticalPadding =
            rememberSaveable(this.innerVerticalPadding) { mutableStateOf(this.innerVerticalPadding) }
        val innerHorizontalPadding =
            rememberSaveable(this.innerHorizontalPadding) { mutableStateOf(this.innerHorizontalPadding) }
        val buttonTextSize =
            rememberSaveable(this.buttonTextSize) { mutableStateOf(this.buttonTextSize) }
        val fontWidth =
            rememberSaveable(this.fontWidth) { mutableStateOf(this.fontWidth) }
        val iconsArrangement =
            rememberSaveable(this.iconsArrangement) { mutableStateOf(this.iconsArrangement) }
        val startIconRes =
            rememberSaveable(this.startIconRes) { mutableStateOf(this.startIconRes) }
        val startIconPadding =
            rememberSaveable(this.startIconPadding) { mutableStateOf(this.startIconPadding) }
        val startIconSize =
            rememberSaveable(this.startIconSize) { mutableStateOf(this.startIconSize) }
        val endIconRes =
            rememberSaveable(this.endIconRes) { mutableStateOf(this.endIconRes) }
        val endIconPadding =
            rememberSaveable(this.endIconPadding) { mutableStateOf(this.endIconPadding) }
        val endIconSize =
            rememberSaveable(this.endIconSize) { mutableStateOf(this.endIconSize) }
        val buttonEnabled =
            rememberSaveable(this.buttonEnabled) { mutableStateOf(this.buttonEnabled) }
        val animatedBoarder =
            rememberSaveable(this.animatedBoarder) { mutableStateOf(this.animatedBoarder) }
        val animationSpeed =
            rememberSaveable(this.animationSpeed) { mutableStateOf(this.animationSpeed) }
        val clickEffectColor =
            rememberSaveable(this.clickEffectColor) { mutableStateOf(this.clickEffectColor) }
        val fontFamily =
            rememberSaveable(this.fontFamily) { mutableStateOf(this.fontFamily) }

        GradientButton(
            buttonText = buttonText.value,
            buttonTextColor = Color(textColor.value),
            topBoarderColor = Color(topBoarderColor.value),
            bottomBoarderColor = Color(bottomBoarderColor.value),
            buttonBackground = Color(buttonBackground.value),
            disabledButtonBackground = Color(disabledButtonBackground.value),
            disabledButtonBackgroundAlpha = disabledButtonBackgroundAlpha.value,
            disabledTextAlpha = disabledTextAlpha.value,
            borderRound = borderRound.value.dp,
            borderWidth = borderWidth.value.dp,
            innerVerticalPadding = innerVerticalPadding.value.dp,
            innerHorizontalPadding = innerHorizontalPadding.value.dp,
            buttonTextSize = buttonTextSize.value.sp,
            buttonEnabled = buttonEnabled.value,
            animatedBoarder = animatedBoarder.value,
            animationSpeed = animationSpeed.value,
            fontWeight = fontWidth.value,
            iconsArrangement = iconsArrangement.value,
            startIconRes = if (startIconRes.value == 0) null else startIconRes.value,
            endIconRes = if (endIconRes.value == 0) null else endIconRes.value,
            startIconSize = startIconSize.value.dp,
            endIconSize = endIconSize.value.dp,
            startIconPadding = startIconPadding.value.dp,
            endIconPadding = endIconPadding.value.dp,
            clickEffectColor = Color(clickEffectColor.value),
            fontFamily = if (fontFamily.value == 0) FontFamily.Default else FontFamily(
                Font(
                    fontFamily.value
                )
            ),
            listener = onClick
        )
    }

    fun setOnDebounceClickListener(onClick: (() -> Unit)?) {
        this.onClick = onClick
    }
}

@Composable
fun GradientButton(
    borderRound: Dp = 0.dp,
    buttonBackground: Color = Color.Red,
    disabledButtonBackground: Color = Color.Gray,
    disabledButtonBackgroundAlpha: Float = 1f,
    disabledTextAlpha: Float = 1f,
    buttonEnabled: Boolean = true,
    animatedBoarder: Boolean = false,
    animationSpeed: Int = 2000,
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
    clickEffectColor: Color = Color.White,
    fontFamily: FontFamily = FontFamily.Default,
    listener: (() -> Unit)? = null,
) {
    var arrangement = when (iconsArrangement) {
        0 -> Arrangement.Center
        1 -> Arrangement.SpaceBetween
        else -> Arrangement.Start
    }

    if (endIconRes == null || startIconRes == null) {
        arrangement = Arrangement.Center
    }

    val transition = rememberInfiniteTransition()

    val topColorAnimated by transition.animateColor(
        initialValue = topBoarderColor,
        targetValue = bottomBoarderColor,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationSpeed, easing = FastOutLinearInEasing
            ), repeatMode = RepeatMode.Reverse
        )
    )

    val bottomColorAnimated by transition.animateColor(
        initialValue = bottomBoarderColor,
        targetValue = topBoarderColor,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationSpeed, easing = FastOutLinearInEasing
            ), repeatMode = RepeatMode.Reverse
        )
    )

    val background = if (buttonEnabled) {
        buttonBackground
    } else {
        if (disabledButtonBackgroundAlpha == 1f) {
            disabledButtonBackground
        } else {
            disabledButtonBackground.copy(alpha = disabledButtonBackgroundAlpha)
        }
    }

    val enabledModifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(RoundedCornerShape(borderRound))
        .background(background)
        .debounceClickable(
            isEnabled = buttonEnabled,
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(
                color = clickEffectColor,
                bounded = false,
                radius = 400.dp
            ),
            listener = listener
        )
        .border(
            borderWidth, Brush.verticalGradient(
                colors = if (buttonEnabled) listOf(
                    if (animatedBoarder) topColorAnimated else topBoarderColor,
                    if (animatedBoarder) bottomColorAnimated else bottomBoarderColor
                ) else listOf(Color.Transparent, Color.Transparent)
            ), RoundedCornerShape(borderRound)
        )
        .clipToBounds()
        .padding(bottom = innerVerticalPadding, top = innerVerticalPadding)

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
                    .padding(0.dp)
                    .alpha(if (buttonEnabled) 1.0f else disabledTextAlpha),
                text = buttonText,
                style = TextStyle(
                    color = buttonTextColor,
                    fontSize = buttonTextSize,
                    fontWeight = FontWeight(fontWeight),
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false,
                    ),
                    fontFamily = fontFamily
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

fun Modifier.debounceClickable(
    debounceInterval: Long = 300,
    isEnabled: Boolean,
    interactionSource: MutableInteractionSource,
    indication: Indication?,
    listener: (() -> Unit)?,
): Modifier = composed {
    var lastClickTime by remember { mutableStateOf(0L) }
    clickable(
        enabled = isEnabled, indication = indication, interactionSource = interactionSource
    ) {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - lastClickTime) < debounceInterval) return@clickable
        lastClickTime = currentTime
        listener?.invoke()
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
                startIconRes = R.drawable.baseline_check_24

            )
            Spacer(modifier = Modifier.height(20.dp))
            GradientButton(
                borderRound = 100.dp,
                borderWidth = 0.dp,
                buttonText = "TestTwo",
                fontWeight = 600,
                buttonEnabled = true,
                buttonTextColor = Color.White,
                startIconRes = R.drawable.baseline_check_24,
                endIconRes = R.drawable.baseline_check_24,
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
                endIconRes = R.drawable.baseline_check_24,
                clickEffectColor = Color.Yellow
            )
        }

    }
}