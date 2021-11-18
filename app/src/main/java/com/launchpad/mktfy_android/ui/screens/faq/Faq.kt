package com.launchpad.mktfy_android.ui.screens.faq

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.ui.components.Header
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.launchpad.mktfy_android.R
import com.launchpad.mktfy_android.ui.theme.*


@ExperimentalAnimationApi
@Composable
fun Faq(
    navigateBack: () -> Unit
) {
    FaqState(
        navigateBack = navigateBack
    )
}


@ExperimentalAnimationApi
@Composable
private fun FaqState(
    viewModel: FaqViewModel = viewModel(),
    navigateBack: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    FaqContent(
        viewState = viewState,
        actioner = { action ->
            when (action) {
                FaqAction.NavigateBack -> navigateBack()
                else -> viewModel.submitAction(action)
            }
        }
    )
    // Temp Questions
    viewModel.getQuestions()
}


@ExperimentalAnimationApi
@Composable
private fun FaqContent(
    viewState: FaqViewState = FaqViewState(),
    actioner: (FaqAction) -> Unit = {}
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
    ) {
        //HEADER
        Header(
            text = "FAQ",
            navigateBack = { actioner(FaqAction.NavigateBack) }
        )

        // Screen 1 - Questions
        AnimatedVisibility(visible = !viewState.showAnswer,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 250))
        ) {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .background(color = LightGrayBackground),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                items(viewState.questions) { question ->
                    QuestionComposable(question, actioner)
                }
            }
        }


        AnimatedVisibility(visible = viewState.showAnswer,
            enter = fadeIn(animationSpec = tween(durationMillis = 750)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .background(
                        color = LightGrayBackground,
                        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    )
                    .verticalScroll(rememberScrollState()),
            ) {
                //Hide answer, reset selected question when back is pressed
                BackHandler(
                    onBack = { actioner(FaqAction.NavigateQuestions) }
                )

                viewState.selectedQuestion?.let {
                    Text(modifier = Modifier
                        .padding(start = 20.dp, top = 30.dp, bottom = 15.dp, end = 20.dp)
                        .fillMaxWidth(),
                        text = it.question,
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = DarkerPurple
                        )
                    )
                    Text(modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 20.dp)
                        .fillMaxWidth(),
                        text = it.answer,
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            color = BlackText
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun QuestionComposable(question: Question, actioner: (FaqAction) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(70.dp)
        .background(color = Color.White)
        .clickable {
            actioner(FaqAction.NavigateAnswer(question))
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(modifier = Modifier
            .padding(start = 20.dp),
            text = question.question,
            style = TextStyle.Default.copy(
                fontFamily = openSansFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = BlackText
            )
        )
        Icon(painter = painterResource(id = R.drawable.arrow_right_24px),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.padding(end = 20.dp))
    }
}


@ExperimentalAnimationApi
@Preview (showBackground = true)
@Composable
private fun FaqPreview() {
    FaqContent(
        viewState = FaqViewState(
            questions = listOf(
                Question(
                    id = 1,
                    question = "What is MKTFY?",
                    answer = "None of your business"
                )
            )
        )
    )
}

@ExperimentalAnimationApi
@Preview (showBackground = true)
@Composable
private fun FaqPreviewAnswers() {
    FaqContent(
        viewState = FaqViewState(
            selectedQuestion = Question(
                    id = 1,
                    question = "What is MKTFY?",
                    answer = "None of your business"
            ),
            showAnswer = true
        )
    )
}