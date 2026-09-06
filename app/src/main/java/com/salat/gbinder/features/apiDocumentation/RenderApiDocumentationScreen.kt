package com.salat.gbinder.features.apiDocumentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salat.gbinder.R
import com.salat.gbinder.components.spannedFromHtml
import com.salat.gbinder.components.toAnnotatedString
import com.salat.gbinder.ui.TopShadow
import com.salat.gbinder.ui.theme.AppTheme

private val documentationSections = listOf(
    R.string.api_text_launcher,
    R.string.api_text,
    R.string.api_text2,
    R.string.api_text3,
    R.string.api_text4,
    R.string.api_text5,
    R.string.api_text6,
    R.string.api_text7,
    R.string.api_text8,
    R.string.api_text9,
    R.string.api_text10,
    R.string.api_text11
)

private val sectionTitlePattern = Regex("^\\s*<strong>(.*?)</strong>")
private val leadingBreaksPattern = Regex("^(\\s|<br>)+")
private val actionPattern = Regex("com\\.salat\\.gbinder(\\.[A-Z0-9_*]+)?")
private val extraNamePattern = Regex("[a-z][A-Za-z0-9_]*(?=\\s*(?:=|->))")

@Composable
fun RenderApiDocumentationScreen(onClose: () -> Unit) {
    BackHandler(onBack = onClose)

    Column(Modifier.fillMaxSize()) {
        RenderApiDocumentationToolbar(onClose)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(AppTheme.colors.lampBackground.copy(.3f))
        ) {
            TopShadow()

            CompositionLocalProvider(LocalOverscrollFactory provides null) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 20.dp, top = 18.dp, end = 20.dp, bottom = 96.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(documentationSections, key = { it }) { sectionId ->
                        RenderApiDocumentationSection(stringResource(sectionId))
                    }
                }
            }
        }
    }
}

@Composable
private fun RenderApiDocumentationToolbar(onClose: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier
                .size(56.dp)
                .padding(start = 2.dp),
            onClick = onClose
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = AppTheme.colors.contentPrimary,
                contentDescription = stringResource(R.string.back)
            )
        }

        Spacer(Modifier.width(16.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.api_documentation_title),
            style = AppTheme.typography.stubTitle,
            color = AppTheme.colors.contentPrimary
        )

        Spacer(Modifier.width(36.dp))
    }
}

@Composable
private fun RenderApiDocumentationSection(raw: String) {
    val contentPrimary = AppTheme.colors.contentPrimary
    val contentAccent = AppTheme.colors.contentAccent
    val contentWarning = AppTheme.colors.contentWarning
    val section = remember(raw, contentPrimary, contentAccent, contentWarning) {
        parseDocumentationSection(raw, contentPrimary, contentAccent, contentWarning)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(AppTheme.colors.cardItemBackground)
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
        section.title?.let { title ->
            Text(
                text = title,
                style = AppTheme.typography.dialogTitle,
                color = contentPrimary
            )

            Spacer(Modifier.height(12.dp))

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(contentPrimary.copy(.1f))
            )

            Spacer(Modifier.height(12.dp))
        }

        SelectionContainer {
            Text(
                text = section.body,
                style = AppTheme.typography.surfaceSubtitle.copy(lineHeight = 20.sp),
                color = contentPrimary.copy(.75f)
            )
        }
    }
}

private data class DocumentationSection(val title: String?, val body: AnnotatedString)

private fun parseDocumentationSection(
    raw: String,
    contentPrimary: Color,
    contentAccent: Color,
    contentWarning: Color
): DocumentationSection {
    val titleMatch = sectionTitlePattern.find(raw)
    val title = titleMatch?.groupValues?.get(1)?.spannedFromHtml()?.toString()?.trim()
    val bodyHtml = if (titleMatch == null) raw else raw.substring(titleMatch.range.last + 1)
        .replaceFirst(leadingBreaksPattern, "")
    val base = bodyHtml.spannedFromHtml().toAnnotatedString()
    val body = buildAnnotatedString {
        append(base)
        base.spanStyles.forEach { span ->
            if (span.item.fontWeight == FontWeight.Bold) {
                addStyle(SpanStyle(color = contentPrimary), span.start, span.end)
            }
        }
        actionPattern.findAll(base.text).forEach { match ->
            addStyle(
                SpanStyle(fontFamily = FontFamily.Monospace, color = contentAccent),
                match.range.first,
                match.range.last + 1
            )
        }
        extraNamePattern.findAll(base.text).forEach { match ->
            addStyle(
                SpanStyle(fontFamily = FontFamily.Monospace, color = contentWarning),
                match.range.first,
                match.range.last + 1
            )
        }
    }
    return DocumentationSection(title, body)
}
