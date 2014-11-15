package com.appjangle.filesync.tests.utils;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.oehme.xtend.contrib.SignatureHelper;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.AnnotationReference;
import org.eclipse.xtend.lib.macro.declaration.Element;
import org.eclipse.xtend.lib.macro.declaration.FieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MemberDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.Modifier;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMemberDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.ResolvedMethod;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.declaration.Visibility;
import org.eclipse.xtend.lib.macro.services.AnnotationReferenceBuildContext;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * From: https://github.com/oehme/xtend-junit
 */
@SuppressWarnings("all")
public class JUnitProcessor extends AbstractClassProcessor {
  @Extension
  private TransformationContext context;
  
  @Extension
  private SignatureHelper signatures;
  
  public void doTransform(final MutableClassDeclaration cls, final TransformationContext context) {
    this.context = context;
    SignatureHelper _signatureHelper = new SignatureHelper(context);
    this.signatures = _signatureHelper;
    this.handleTestMethods(cls);
    this.handleRules(cls);
    this.handleTheories(cls);
    this.importAssert(cls);
    this.importJUnitExtensions(cls);
  }
  
  public void handleTestMethods(final MutableClassDeclaration cls) {
    Iterable<? extends MutableMethodDeclaration> _declaredMethods = cls.getDeclaredMethods();
    final Function1<MutableMethodDeclaration, Boolean> _function = new Function1<MutableMethodDeclaration, Boolean>() {
      public Boolean apply(final MutableMethodDeclaration it) {
        boolean _and = false;
        boolean _and_1 = false;
        boolean _and_2 = false;
        boolean _and_3 = false;
        Visibility _visibility = it.getVisibility();
        boolean _equals = Objects.equal(_visibility, Visibility.PUBLIC);
        if (!_equals) {
          _and_3 = false;
        } else {
          boolean _isStatic = it.isStatic();
          boolean _equals_1 = (_isStatic == false);
          _and_3 = _equals_1;
        }
        if (!_and_3) {
          _and_2 = false;
        } else {
          Type _findTypeGlobally = JUnitProcessor.this.context.findTypeGlobally(Theory.class);
          AnnotationReference _findAnnotation = it.findAnnotation(_findTypeGlobally);
          boolean _equals_2 = Objects.equal(_findAnnotation, null);
          _and_2 = _equals_2;
        }
        if (!_and_2) {
          _and_1 = false;
        } else {
          Type _findTypeGlobally_1 = JUnitProcessor.this.context.findTypeGlobally(DataPoint.class);
          AnnotationReference _findAnnotation_1 = it.findAnnotation(_findTypeGlobally_1);
          boolean _equals_3 = Objects.equal(_findAnnotation_1, null);
          _and_1 = _equals_3;
        }
        if (!_and_1) {
          _and = false;
        } else {
          Type _findTypeGlobally_2 = JUnitProcessor.this.context.findTypeGlobally(DataPoints.class);
          AnnotationReference _findAnnotation_2 = it.findAnnotation(_findTypeGlobally_2);
          boolean _equals_4 = Objects.equal(_findAnnotation_2, null);
          _and = _equals_4;
        }
        return Boolean.valueOf(_and);
      }
    };
    Iterable<? extends MutableMethodDeclaration> _filter = IterableExtensions.filter(_declaredMethods, _function);
    final Consumer<MutableMethodDeclaration> _function_1 = new Consumer<MutableMethodDeclaration>() {
      public void accept(final MutableMethodDeclaration it) {
        Iterable<? extends MutableParameterDeclaration> _parameters = it.getParameters();
        int _size = IterableExtensions.size(_parameters);
        boolean _notEquals = (_size != 0);
        if (_notEquals) {
          JUnitProcessor.this.context.addError(it, "Test methods cannot take parameters");
        }
        boolean _and = false;
        TypeReference _returnType = it.getReturnType();
        boolean _isInferred = _returnType.isInferred();
        boolean _not = (!_isInferred);
        if (!_not) {
          _and = false;
        } else {
          TypeReference _returnType_1 = it.getReturnType();
          TypeReference _primitiveVoid = JUnitProcessor.this.context.getPrimitiveVoid();
          boolean _notEquals_1 = (!Objects.equal(_returnType_1, _primitiveVoid));
          _and = _notEquals_1;
        }
        if (_and) {
          JUnitProcessor.this.context.addError(it, "Test methods always return void, you can leave out the return type");
        }
        Type _findTypeGlobally = JUnitProcessor.this.context.findTypeGlobally(Test.class);
        AnnotationReference _findAnnotation = it.findAnnotation(_findTypeGlobally);
        boolean _equals = Objects.equal(_findAnnotation, null);
        if (_equals) {
          AnnotationReference _newAnnotationReference = JUnitProcessor.this.context.newAnnotationReference(Test.class);
          it.addAnnotation(_newAnnotationReference);
        }
        TypeReference _primitiveVoid_1 = JUnitProcessor.this.context.getPrimitiveVoid();
        it.setReturnType(_primitiveVoid_1);
      }
    };
    _filter.forEach(_function_1);
  }
  
  public void handleRules(final MutableClassDeclaration cls) {
    Iterable<? extends MutableFieldDeclaration> _declaredFields = cls.getDeclaredFields();
    final Function1<MutableFieldDeclaration, Boolean> _function = new Function1<MutableFieldDeclaration, Boolean>() {
      public Boolean apply(final MutableFieldDeclaration it) {
        Type _findTypeGlobally = JUnitProcessor.this.context.findTypeGlobally(Rule.class);
        AnnotationReference _findAnnotation = it.findAnnotation(_findTypeGlobally);
        return Boolean.valueOf((!Objects.equal(_findAnnotation, null)));
      }
    };
    Iterable<? extends MutableFieldDeclaration> _filter = IterableExtensions.filter(_declaredFields, _function);
    final Consumer<MutableFieldDeclaration> _function_1 = new Consumer<MutableFieldDeclaration>() {
      public void accept(final MutableFieldDeclaration it) {
        Element _primarySourceElement = JUnitProcessor.this.context.getPrimarySourceElement(it);
        final FieldDeclaration source = ((FieldDeclaration) _primarySourceElement);
        final Set<Modifier> forbiddenModifiers = Collections.<Modifier>unmodifiableSet(CollectionLiterals.<Modifier>newHashSet(Modifier.PRIVATE, Modifier.PROTECTED, Modifier.PACKAGE, Modifier.STATIC));
        for (final Modifier mod : forbiddenModifiers) {
          Set<Modifier> _modifiers = source.getModifiers();
          boolean _contains = _modifiers.contains(mod);
          if (_contains) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("Rules cannot be ");
            _builder.append(mod, "");
            JUnitProcessor.this.context.addError(it, _builder.toString());
          }
        }
        it.setVisibility(Visibility.PUBLIC);
      }
    };
    _filter.forEach(_function_1);
  }
  
  public void handleTheories(final MutableClassDeclaration cls) {
    Iterable<? extends MutableMemberDeclaration> _declaredMembers = cls.getDeclaredMembers();
    final Function1<MutableMemberDeclaration, Boolean> _function = new Function1<MutableMemberDeclaration, Boolean>() {
      public Boolean apply(final MutableMemberDeclaration it) {
        Type _findTypeGlobally = JUnitProcessor.this.context.findTypeGlobally(DataPoint.class);
        AnnotationReference _findAnnotation = it.findAnnotation(_findTypeGlobally);
        return Boolean.valueOf((!Objects.equal(_findAnnotation, null)));
      }
    };
    final Iterable<? extends MutableMemberDeclaration> dataPointMembers = IterableExtensions.filter(_declaredMembers, _function);
    Iterable<? extends MutableMemberDeclaration> _declaredMembers_1 = cls.getDeclaredMembers();
    final Function1<MutableMemberDeclaration, Boolean> _function_1 = new Function1<MutableMemberDeclaration, Boolean>() {
      public Boolean apply(final MutableMemberDeclaration it) {
        Type _findTypeGlobally = JUnitProcessor.this.context.findTypeGlobally(DataPoints.class);
        AnnotationReference _findAnnotation = it.findAnnotation(_findTypeGlobally);
        return Boolean.valueOf((!Objects.equal(_findAnnotation, null)));
      }
    };
    final Iterable<? extends MutableMemberDeclaration> dataPointsMembers = IterableExtensions.filter(_declaredMembers_1, _function_1);
    Iterable<? extends MutableMethodDeclaration> _declaredMethods = cls.getDeclaredMethods();
    final Function1<MutableMethodDeclaration, Boolean> _function_2 = new Function1<MutableMethodDeclaration, Boolean>() {
      public Boolean apply(final MutableMethodDeclaration it) {
        Type _findTypeGlobally = JUnitProcessor.this.context.findTypeGlobally(Theory.class);
        AnnotationReference _findAnnotation = it.findAnnotation(_findTypeGlobally);
        return Boolean.valueOf((!Objects.equal(_findAnnotation, null)));
      }
    };
    final Iterable<? extends MutableMethodDeclaration> theories = IterableExtensions.filter(_declaredMethods, _function_2);
    Iterable<MutableMemberDeclaration> _plus = Iterables.<MutableMemberDeclaration>concat(dataPointMembers, dataPointsMembers);
    final Consumer<MutableMemberDeclaration> _function_3 = new Consumer<MutableMemberDeclaration>() {
      public void accept(final MutableMemberDeclaration it) {
        Element _primarySourceElement = JUnitProcessor.this.context.getPrimarySourceElement(it);
        final MemberDeclaration source = ((MemberDeclaration) _primarySourceElement);
        final Set<Modifier> forbiddenModifiers = Collections.<Modifier>unmodifiableSet(CollectionLiterals.<Modifier>newHashSet(Modifier.PRIVATE, Modifier.PROTECTED, Modifier.PACKAGE));
        for (final Modifier mod : forbiddenModifiers) {
          Set<Modifier> _modifiers = source.getModifiers();
          boolean _contains = _modifiers.contains(mod);
          if (_contains) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("DataPoints cannot be ");
            _builder.append(mod, "");
            JUnitProcessor.this.context.addError(it, _builder.toString());
          }
        }
        JUnitProcessor.this.makeStatic(it);
        it.setVisibility(Visibility.PUBLIC);
      }
    };
    _plus.forEach(_function_3);
    boolean _and = false;
    boolean _isEmpty = IterableExtensions.isEmpty(theories);
    boolean _not = (!_isEmpty);
    if (!_not) {
      _and = false;
    } else {
      Type _findTypeGlobally = this.context.findTypeGlobally(RunWith.class);
      AnnotationReference _findAnnotation = cls.findAnnotation(_findTypeGlobally);
      boolean _equals = Objects.equal(_findAnnotation, null);
      _and = _equals;
    }
    if (_and) {
      this.importAssume(cls);
      final Procedure1<AnnotationReferenceBuildContext> _function_4 = new Procedure1<AnnotationReferenceBuildContext>() {
        public void apply(final AnnotationReferenceBuildContext it) {
          TypeReference _newTypeReference = JUnitProcessor.this.context.newTypeReference(Theories.class);
          it.setClassValue("value", _newTypeReference);
        }
      };
      AnnotationReference _newAnnotationReference = this.context.newAnnotationReference(RunWith.class, _function_4);
      cls.addAnnotation(_newAnnotationReference);
    }
    final Procedure0 _function_5 = new Procedure0() {
      public void apply() {
        final Consumer<MutableMemberDeclaration> _function = new Consumer<MutableMemberDeclaration>() {
          public void accept(final MutableMemberDeclaration it) {
            TypeReference _type = JUnitProcessor.this.getType(it);
            boolean _isArray = _type.isArray();
            boolean _not = (!_isArray);
            if (_not) {
              JUnitProcessor.this.context.addError(it, "DataPoints must return an array");
            }
          }
        };
        dataPointsMembers.forEach(_function);
        final HashSet<TypeReference> dataPointTypes = CollectionLiterals.<TypeReference>newHashSet();
        final Function1<MutableMemberDeclaration, TypeReference> _function_1 = new Function1<MutableMemberDeclaration, TypeReference>() {
          public TypeReference apply(final MutableMemberDeclaration it) {
            return JUnitProcessor.this.getType(it);
          }
        };
        Iterable<TypeReference> _map = IterableExtensions.map(dataPointMembers, _function_1);
        Iterables.<TypeReference>addAll(dataPointTypes, _map);
        final Function1<MutableMemberDeclaration, TypeReference> _function_2 = new Function1<MutableMemberDeclaration, TypeReference>() {
          public TypeReference apply(final MutableMemberDeclaration it) {
            return JUnitProcessor.this.getComponentType(it);
          }
        };
        Iterable<TypeReference> _map_1 = IterableExtensions.map(dataPointsMembers, _function_2);
        Iterables.<TypeReference>addAll(dataPointTypes, _map_1);
        final Consumer<MutableMethodDeclaration> _function_3 = new Consumer<MutableMethodDeclaration>() {
          public void accept(final MutableMethodDeclaration it) {
            Iterable<? extends MutableParameterDeclaration> _parameters = it.getParameters();
            final Consumer<MutableParameterDeclaration> _function = new Consumer<MutableParameterDeclaration>() {
              public void accept(final MutableParameterDeclaration it) {
                TypeReference _type = it.getType();
                boolean _contains = dataPointTypes.contains(_type);
                boolean _not = (!_contains);
                if (_not) {
                  StringConcatenation _builder = new StringConcatenation();
                  _builder.append("No @DataPoints of type ");
                  TypeReference _type_1 = it.getType();
                  String _simpleName = _type_1.getSimpleName();
                  _builder.append(_simpleName, "");
                  _builder.append(" specified");
                  JUnitProcessor.this.context.addError(it, _builder.toString());
                }
              }
            };
            _parameters.forEach(_function);
          }
        };
        theories.forEach(_function_3);
      }
    };
    this.context.validateLater(_function_5);
  }
  
  public void importAssert(final MutableClassDeclaration cls) {
    TypeReference _newTypeReference = this.context.newTypeReference(Assert.class);
    Iterable<? extends ResolvedMethod> _declaredResolvedMethods = _newTypeReference.getDeclaredResolvedMethods();
    final Function1<ResolvedMethod, Boolean> _function = new Function1<ResolvedMethod, Boolean>() {
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
    Iterable<? extends ResolvedMethod> _filter = IterableExtensions.filter(_declaredResolvedMethods, _function);
    final Consumer<ResolvedMethod> _function_1 = new Consumer<ResolvedMethod>() {
      public void accept(final ResolvedMethod m) {
        MethodDeclaration _declaration = m.getDeclaration();
        String _simpleName = _declaration.getSimpleName();
        final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
          public void apply(final MutableMethodDeclaration it) {
            JUnitProcessor.this.signatures.copySignatureFrom(it, m);
            it.setVisibility(Visibility.PRIVATE);
            JUnitProcessor.this.context.setPrimarySourceElement(it, cls);
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append(Assert.class, "");
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
    _filter.forEach(_function_1);
  }
  
  public void importAssume(final MutableClassDeclaration cls) {
    TypeReference _newTypeReference = this.context.newTypeReference(Assume.class);
    Iterable<? extends ResolvedMethod> _declaredResolvedMethods = _newTypeReference.getDeclaredResolvedMethods();
    final Function1<ResolvedMethod, Boolean> _function = new Function1<ResolvedMethod, Boolean>() {
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
    Iterable<? extends ResolvedMethod> _filter = IterableExtensions.filter(_declaredResolvedMethods, _function);
    final Consumer<ResolvedMethod> _function_1 = new Consumer<ResolvedMethod>() {
      public void accept(final ResolvedMethod m) {
        MethodDeclaration _declaration = m.getDeclaration();
        String _simpleName = _declaration.getSimpleName();
        final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
          public void apply(final MutableMethodDeclaration it) {
            JUnitProcessor.this.signatures.copySignatureFrom(it, m);
            it.setVisibility(Visibility.PRIVATE);
            JUnitProcessor.this.context.setPrimarySourceElement(it, cls);
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append(Assume.class, "");
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
    _filter.forEach(_function_1);
  }
  
  public MutableMethodDeclaration importJUnitExtensions(final MutableClassDeclaration cls) {
    MutableMethodDeclaration _xblockexpression = null;
    {
      final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setVisibility(Visibility.PRIVATE);
          it.setStatic(true);
          TypeReference _object = JUnitProcessor.this.context.getObject();
          MutableTypeParameterDeclaration _addTypeParameter = it.addTypeParameter("T", _object);
          final TypeReference t = JUnitProcessor.this.context.newTypeReference(_addTypeParameter);
          MutableTypeParameterDeclaration _addTypeParameter_1 = it.addTypeParameter("U", t);
          final TypeReference u = JUnitProcessor.this.context.newTypeReference(_addTypeParameter_1);
          it.addParameter("actual", t);
          it.addParameter("expected", u);
          JUnitProcessor.this.context.setPrimarySourceElement(it, cls);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append(Assert.class, "");
              _builder.append(".assertEquals(expected, actual);");
              _builder.newLineIfNotEmpty();
            }
          };
          it.setBody(_client);
        }
      };
      cls.addMethod("operator_doubleArrow", _function);
      final Procedure1<MutableMethodDeclaration> _function_1 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setVisibility(Visibility.PRIVATE);
          it.setStatic(true);
          TypeReference _newTypeReference = JUnitProcessor.this.context.newTypeReference(Exception.class);
          MutableTypeParameterDeclaration _addTypeParameter = it.addTypeParameter("T", _newTypeReference);
          final TypeReference t = JUnitProcessor.this.context.newTypeReference(_addTypeParameter);
          TypeReference _newTypeReference_1 = JUnitProcessor.this.context.newTypeReference(Class.class, t);
          it.addParameter("expected", _newTypeReference_1);
          TypeReference _newTypeReference_2 = JUnitProcessor.this.context.newTypeReference(Procedure0.class);
          it.addParameter("block", _newTypeReference_2);
          JUnitProcessor.this.context.setPrimarySourceElement(it, cls);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("try {");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("block.apply();");
              _builder.newLine();
              _builder.append("\t");
              _builder.append(Assert.class, "\t");
              _builder.append(".fail(\"Expected a \" + expected.getName());");
              _builder.newLineIfNotEmpty();
              _builder.append("} catch (Exception e) {");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("Class<?> actual = e.getClass();");
              _builder.newLine();
              _builder.append("\t");
              _builder.append(Assert.class, "\t");
              _builder.append(".assertTrue(");
              _builder.newLineIfNotEmpty();
              _builder.append("\t\t");
              _builder.append("\"Expected a \" + expected.getName() + \" but got \" + actual.getName(), ");
              _builder.newLine();
              _builder.append("\t\t");
              _builder.append("expected.isAssignableFrom(actual)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append(");");
              _builder.newLine();
              _builder.append("}");
              _builder.newLine();
            }
          };
          it.setBody(_client);
        }
      };
      _xblockexpression = cls.addMethod("isThrownBy", _function_1);
    }
    return _xblockexpression;
  }
  
  protected void _makeStatic(final MutableFieldDeclaration member) {
    member.setStatic(true);
  }
  
  protected void _makeStatic(final MutableMethodDeclaration member) {
    member.setStatic(true);
  }
  
  public TypeReference getComponentType(final MutableMemberDeclaration member) {
    final TypeReference type = this.getType(member);
    boolean _isArray = type.isArray();
    if (_isArray) {
      return type.getArrayComponentType();
    } else {
      List<TypeReference> _actualTypeArguments = type.getActualTypeArguments();
      int _size = _actualTypeArguments.size();
      boolean _equals = (_size == 1);
      if (_equals) {
        List<TypeReference> _actualTypeArguments_1 = type.getActualTypeArguments();
        return IterableExtensions.<TypeReference>head(_actualTypeArguments_1);
      } else {
        return this.context.getObject();
      }
    }
  }
  
  protected TypeReference _getType(final MutableFieldDeclaration member) {
    return member.getType();
  }
  
  protected TypeReference _getType(final MutableMethodDeclaration member) {
    return member.getReturnType();
  }
  
  public void makeStatic(final MutableMemberDeclaration member) {
    if (member instanceof MutableMethodDeclaration) {
      _makeStatic((MutableMethodDeclaration)member);
      return;
    } else if (member instanceof MutableFieldDeclaration) {
      _makeStatic((MutableFieldDeclaration)member);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(member).toString());
    }
  }
  
  public TypeReference getType(final MutableMemberDeclaration member) {
    if (member instanceof MutableMethodDeclaration) {
      return _getType((MutableMethodDeclaration)member);
    } else if (member instanceof MutableFieldDeclaration) {
      return _getType((MutableFieldDeclaration)member);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(member).toString());
    }
  }
}
