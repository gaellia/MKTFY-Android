package com.launchpad.mktfy_android.ui.screens.faq

import androidx.lifecycle.viewModelScope
import com.launchpad.mktfy_android.core.ActionViewModel
import kotlinx.coroutines.launch

class FaqViewModel : ActionViewModel<FaqViewState, FaqAction>(FaqViewState()) {
    override fun collectAction(action: FaqAction) {
        when (action) {
            is FaqAction.NavigateAnswer -> navigateAnswer(action.selectedQuestion)
            FaqAction.NavigateBack -> { }
            FaqAction.NavigateQuestions -> hideAnswer()
        }
    }

    private fun navigateAnswer(q: Question){
        viewModelScope.launchSetState { copy(selectedQuestion = q, showAnswer = true) }
    }

    private fun hideAnswer() {
        viewModelScope.launchSetState { copy(selectedQuestion = null, showAnswer = false) }
    }

    fun getQuestions() {
        viewModelScope.launch {
            launchSetState {
                copy(
                    questions = listOf (
                        Question(
                            id = 0,
                            question = "How MKTFY works?",
                            answer = "These Terms of Service constitute a legally binding agreement made between you, " +
                                    "whether personally or on behalf of an entity (“you”) and [business entity name] (“we,” “us” or “our”), " +
                                    "concerning your access to and use of the [website name.com] website as well as any other media form, " +
                                    "media channel, mobile website or mobile application related, linked, " +
                                    "or otherwise connected thereto (collectively, the “Site”). " +
                                    "You agree that by accessing the Site, you have read, understood, " +
                                    "and agree to be bound by all of these Terms of Service. " +
                                    "If you do not agree with all of these Terms of Service, " +
                                    "then you are expressly prohibited from using the Site and you must discontinue use immediately. " +
                                    "Supplemental Terms of Service or documents that may be posted on the Site from time to time are " +
                                    "hereby expressly incorporated herein by reference. We reserve the right, in our sole discretion, " +
                                    "to make changes or modifications to these Terms of Service at any time and for any reason. " +
                                    "We will alert you about any changes by updating the “Last updated” date of these Terms of Service, " +
                                    "and you waive any right to receive specific notice of each such change. " +
                                    "It is your responsibility to periodically review these Terms of Service to stay informed of updates. " +
                                    "You will be subject to, and will be deemed to have been made aware of and to have accepted, " +
                                    "the changes in any revised Terms of Service by your continued use of the Site after the date such " +
                                    "revised Terms of Service are posted. The information provided on the Site is not intended for " +
                                    "distribution to or use by any person or entity in any jurisdiction or country where such " +
                                    "distribution or use would be contrary to law or regulation or which would subject us to " +
                                    "any registration requirement within such jurisdiction or country."
                        ),
                        Question(
                            id = 1,
                            question = "How I can sell things on MKTFY",
                            answer = "You can sell things by constituting a legally binding agreement made between you, " +
                                    "whether personally or on behalf of an entity (“you”) and [business entity name] (“we,” “us” or “our”), " +
                                    "concerning your access to and use of the [website name.com] website as well as any other media form, " +
                                    "media channel, mobile website or mobile application related, linked, " +
                                    "or otherwise connected thereto (collectively, the “Site”). " +
                                    "You agree that by accessing the Site, you have read, understood, " +
                                    "and agree to be bound by all of these Terms of Service. " +
                                    "If you do not agree with all of these Terms of Service, " +
                                    "then you are expressly prohibited from using the Site and you must discontinue use immediately. " +
                                    "Supplemental Terms of Service or documents that may be posted on the Site from time to time are " +
                                    "hereby expressly incorporated herein by reference. We reserve the right, in our sole discretion, " +
                                    "to make changes or modifications to these Terms of Service at any time and for any reason. " +
                                    "We will alert you about any changes by updating the “Last updated” date of these Terms of Service, " +
                                    "and you waive any right to receive specific notice of each such change. " +
                                    "It is your responsibility to periodically review these Terms of Service to stay informed of updates. " +
                                    "You will be subject to, and will be deemed to have been made aware of and to have accepted, " +
                                    "the changes in any revised Terms of Service by your continued use of the Site after the date such " +
                                    "revised Terms of Service are posted. The information provided on the Site is not intended for " +
                                    "distribution to or use by any person or entity in any jurisdiction or country where such " +
                                    "distribution or use would be contrary to law or regulation or which would subject us to " +
                                    "any registration requirement within such jurisdiction or country."
                        ),
                        Question(
                            id = 2,
                            question = "Where the products come from?",
                            answer = "These products come from a legally binding agreement made between you, " +
                                    "whether personally or on behalf of an entity (“you”) and [business entity name] (“we,” “us” or “our”), " +
                                    "concerning your access to and use of the [website name.com] website as well as any other media form, " +
                                    "media channel, mobile website or mobile application related, linked, " +
                                    "or otherwise connected thereto (collectively, the “Site”). " +
                                    "You agree that by accessing the Site, you have read, understood, " +
                                    "and agree to be bound by all of these Terms of Service. " +
                                    "If you do not agree with all of these Terms of Service, " +
                                    "then you are expressly prohibited from using the Site and you must discontinue use immediately. " +
                                    "Supplemental Terms of Service or documents that may be posted on the Site from time to time are " +
                                    "hereby expressly incorporated herein by reference. We reserve the right, in our sole discretion, " +
                                    "to make changes or modifications to these Terms of Service at any time and for any reason. " +
                                    "We will alert you about any changes by updating the “Last updated” date of these Terms of Service, " +
                                    "and you waive any right to receive specific notice of each such change. " +
                                    "It is your responsibility to periodically review these Terms of Service to stay informed of updates. " +
                                    "You will be subject to, and will be deemed to have been made aware of and to have accepted, " +
                                    "the changes in any revised Terms of Service by your continued use of the Site after the date such " +
                                    "revised Terms of Service are posted. The information provided on the Site is not intended for " +
                                    "distribution to or use by any person or entity in any jurisdiction or country where such " +
                                    "distribution or use would be contrary to law or regulation or which would subject us to " +
                                    "any registration requirement within such jurisdiction or country."
                        ),
                        Question(
                            id = 3,
                            question = "Can I have a refund?",
                            answer = "No"
                        ),
                        Question(
                            id = 4,
                            question = "Where MKTFY is based?",
                            answer = "These products are based from a legally binding agreement made between you, " +
                                    "whether personally or on behalf of an entity (“you”) and [business entity name] (“we,” “us” or “our”), " +
                                    "concerning your access to and use of the [website name.com] website as well as any other media form, " +
                                    "media channel, mobile website or mobile application related, linked, " +
                                    "or otherwise connected thereto (collectively, the “Site”). " +
                                    "You agree that by accessing the Site, you have read, understood, " +
                                    "and agree to be bound by all of these Terms of Service. " +
                                    "If you do not agree with all of these Terms of Service, " +
                                    "then you are expressly prohibited from using the Site and you must discontinue use immediately. " +
                                    "Supplemental Terms of Service or documents that may be posted on the Site from time to time are " +
                                    "hereby expressly incorporated herein by reference. We reserve the right, in our sole discretion, " +
                                    "to make changes or modifications to these Terms of Service at any time and for any reason. " +
                                    "We will alert you about any changes by updating the “Last updated” date of these Terms of Service, " +
                                    "and you waive any right to receive specific notice of each such change. " +
                                    "It is your responsibility to periodically review these Terms of Service to stay informed of updates. " +
                                    "You will be subject to, and will be deemed to have been made aware of and to have accepted, " +
                                    "the changes in any revised Terms of Service by your continued use of the Site after the date such " +
                                    "revised Terms of Service are posted. The information provided on the Site is not intended for " +
                                    "distribution to or use by any person or entity in any jurisdiction or country where such " +
                                    "distribution or use would be contrary to law or regulation or which would subject us to " +
                                    "any registration requirement within such jurisdiction or country."
                        )
                    )
                )
            }
        }
    }
}