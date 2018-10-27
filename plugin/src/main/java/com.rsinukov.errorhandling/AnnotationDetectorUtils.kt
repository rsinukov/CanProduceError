package com.rsinukov.errorhandling

import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiModifierListOwner

internal object AnnotationDetectorUtils {

    fun hasAnnotation(element: PsiElement, annotationName: String): Boolean {
        return findAnnotation(element, annotationName) != null
    }

    fun findAnnotation(element: PsiElement, annotationName: String): PsiAnnotation? {
        return (element as? PsiModifierListOwner)
                ?.modifierList
                ?.annotations
                ?.firstOrNull { annotationName == it.qualifiedName }
    }
}