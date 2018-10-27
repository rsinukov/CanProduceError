package com.rsinukov.errorhandling

import com.intellij.codeHighlighting.Pass
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.util.IconLoader
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiReferenceExpression
import com.intellij.util.Function
import org.jetbrains.kotlin.psi.KtCallExpression


private val ICON = IconLoader.getIcon("/icons/warning_icon.png")

private val TOOLTIP_PROVIDER = Function<PsiElement, String> { "Can produce error. Don't forget about error handling!" }


object EmptyCanProduceErrorLineMarkerProvider : LineMarkerProvider {

    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*> {
        throw NotImplementedError()
    }

    override fun collectSlowLineMarkers(
            psiElements: List<PsiElement>,
            lineMarkerInfos: Collection<LineMarkerInfo<*>>
    ) {
        // no op
    }
}

class JavaCanProduceErrorLineMarkerProvider : LineMarkerProvider by EmptyCanProduceErrorLineMarkerProvider {

    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? {
        if (element !is PsiReferenceExpression) return null
        val method = (element.resolve() as? PsiMethod) ?: return null

//        if (!AnnotationDetectorUtils.hasAnnotation(method, CanProduceError::class.java.name)) return null

        return LineMarkerInfo<com.intellij.psi.PsiElement>(
                element,
                element.getTextRange(),
                ICON,
                Pass.UPDATE_ALL,
                TOOLTIP_PROVIDER,
                null,
                GutterIconRenderer.Alignment.LEFT
        )
    }
}

class KotlinCanProduceErrorLineMarkerProvider : LineMarkerProvider by EmptyCanProduceErrorLineMarkerProvider {

    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? {
        if (element !is KtCallExpression) return null

        return null
    }
}
