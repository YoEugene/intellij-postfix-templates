package de.endrullis.idea.postfixtemplates.languages.sql;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.sql.psi.SqlType;
import de.endrullis.idea.postfixtemplates.language.CptLangAnnotator;
import de.endrullis.idea.postfixtemplates.templates.SpecialType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Code annotator for SQL CPTs.
 *
 * @author Stefan Endrullis &lt;stefan@endrullis.de&gt;
 */
public class SqlAnnotator implements CptLangAnnotator {

	private final Map<String, Boolean> className2exists = new HashMap<String, Boolean>() {{
		put(SpecialType.ANY.name(), true);
		/*
		for (SqlType.Category category : SqlType.Category.values()) {
			put(category.name(), true);
		}
		 */
	}};

	@Override
	public boolean isMatchingType(@NotNull final LeafPsiElement element, @NotNull final String className) {
		return className2exists.containsKey(className);
	}

	@Override
	public void completeMatchingType(@NotNull CompletionParameters parameters, @NotNull CompletionResultSet resultSet) {
		resultSet.addElement(LookupElementBuilder.create(SpecialType.ANY.name()));
		/*
		for (SqlType.Category category : SqlType.Category.values()) {
			resultSet.addElement(LookupElementBuilder.create(category.name()));
		}
		 */
	}

}
