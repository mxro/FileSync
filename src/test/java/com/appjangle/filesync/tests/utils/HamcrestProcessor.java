package com.appjangle.filesync.tests.utils;

import com.google.common.base.Objects;
import de.oehme.xtend.contrib.SignatureHelper;
import java.util.function.Consumer;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.AnnotationReference;
import org.eclipse.xtend.lib.macro.declaration.MethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.ResolvedMethod;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.declaration.Visibility;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;

/**
 * From: https://github.com/oehme/xtend-junit
 */
@SuppressWarnings("all")
public class HamcrestProcessor extends AbstractClassProcessor {
  public void doTransform(final MutableClassDeclaration cls, @Extension final TransformationContext context) {
    @Extension
    final SignatureHelper SignatureHelper = new de.oehme.xtend.contrib.SignatureHelper(context);
    final Procedure1<MutableFieldDeclaration> _function = new Procedure1<MutableFieldDeclaration>() {
      public void apply(final MutableFieldDeclaration it) {
        TypeReference _newTypeReference = context.newTypeReference(ErrorCollector.class);
        it.setType(_newTypeReference);
        it.setVisibility(Visibility.PUBLIC);
        it.setFinal(true);
        AnnotationReference _newAnnotationReference = context.newAnnotationReference(Rule.class);
        it.addAnnotation(_newAnnotationReference);
        AnnotationReference _newAnnotationReference_1 = context.newAnnotationReference(Extension.class);
        it.addAnnotation(_newAnnotationReference_1);
        context.setPrimarySourceElement(it, cls);
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            _builder.append("new ");
            _builder.append(ErrorCollector.class, "");
            _builder.append("()");
          }
        };
        it.setInitializer(_client);
      }
    };
    cls.addField("_errorCollector", _function);
    final Procedure1<MutableMethodDeclaration> _function_1 = new Procedure1<MutableMethodDeclaration>() {
      public void apply(final MutableMethodDeclaration it) {
        it.setVisibility(Visibility.PRIVATE);
        TypeReference _object = context.getObject();
        MutableTypeParameterDeclaration _addTypeParameter = it.addTypeParameter("T", _object);
        final TypeReference t = context.newTypeReference(_addTypeParameter);
        it.addParameter("object", t);
        TypeReference _newWildcardTypeReferenceWithLowerBound = context.newWildcardTypeReferenceWithLowerBound(t);
        TypeReference _newTypeReference = context.newTypeReference(Matcher.class, _newWildcardTypeReferenceWithLowerBound);
        it.addParameter("matcher", _newTypeReference);
        context.setPrimarySourceElement(it, cls);
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            _builder.append(Assert.class, "");
            _builder.append(".assertThat(object, matcher);");
            _builder.newLineIfNotEmpty();
          }
        };
        it.setBody(_client);
      }
    };
    cls.addMethod("operator_doubleArrow", _function_1);
    Type _elvis = null;
    Type _findTypeGlobally = context.findTypeGlobally("org.hamcrest.Matchers");
    if (_findTypeGlobally != null) {
      _elvis = _findTypeGlobally;
    } else {
      Type _findTypeGlobally_1 = context.findTypeGlobally("org.hamcrest.CoreMatchers");
      _elvis = _findTypeGlobally_1;
    }
    final Type matchers = _elvis;
    TypeReference _newTypeReference = context.newTypeReference(matchers);
    Iterable<? extends ResolvedMethod> _declaredResolvedMethods = _newTypeReference.getDeclaredResolvedMethods();
    final Function1<ResolvedMethod, Boolean> _function_2 = new Function1<ResolvedMethod, Boolean>() {
      public Boolean apply(final ResolvedMethod it) {
        boolean _and = false;
        MethodDeclaration _declaration = it.getDeclaration();
        Visibility _visibility = _declaration.getVisibility();
        boolean _equals = Objects.equal(_visibility, Visibility.PUBLIC);
        if (!_equals) {
          _and = false;
        } else {
          MethodDeclaration _declaration_1 = it.getDeclaration();
          boolean _isDeprecated = _declaration_1.isDeprecated();
          boolean _equals_1 = (_isDeprecated == false);
          _and = _equals_1;
        }
        return Boolean.valueOf(_and);
      }
    };
    Iterable<? extends ResolvedMethod> _filter = IterableExtensions.filter(_declaredResolvedMethods, _function_2);
    final Consumer<ResolvedMethod> _function_3 = new Consumer<ResolvedMethod>() {
      public void accept(final ResolvedMethod m) {
        MethodDeclaration _declaration = m.getDeclaration();
        String _simpleName = _declaration.getSimpleName();
        final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
          public void apply(final MutableMethodDeclaration it) {
            SignatureHelper.copySignatureFrom(it, m);
            it.setVisibility(Visibility.PRIVATE);
            context.setPrimarySourceElement(it, cls);
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append("return ");
                _builder.append(matchers, "");
                _builder.append(".");
                Iterable<? extends MutableTypeParameterDeclaration> _typeParameters = it.getTypeParameters();
                final Function1<MutableTypeParameterDeclaration, String> _function = new Function1<MutableTypeParameterDeclaration, String>() {
                  public String apply(final MutableTypeParameterDeclaration it) {
                    return it.getSimpleName();
                  }
                };
                String _join = IterableExtensions.join(_typeParameters, "<", ",", ">", _function);
                _builder.append(_join, "");
                String _simpleName = it.getSimpleName();
                _builder.append(_simpleName, "");
                _builder.append("(");
                Iterable<? extends MutableParameterDeclaration> _parameters = it.getParameters();
                final Function1<MutableParameterDeclaration, String> _function_1 = new Function1<MutableParameterDeclaration, String>() {
                  public String apply(final MutableParameterDeclaration it) {
                    return it.getSimpleName();
                  }
                };
                String _join_1 = IterableExtensions.join(_parameters, ", ", _function_1);
                _builder.append(_join_1, "");
                _builder.append(");");
                _builder.newLineIfNotEmpty();
              }
            };
            it.setBody(_client);
          }
        };
        cls.addMethod(_simpleName, _function);
      }
    };
    _filter.forEach(_function_3);
  }
}
