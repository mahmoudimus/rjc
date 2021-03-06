package org.rjava.compiler.targets.c;

import org.rjava.compiler.targets.c.runtime.CLanguageRuntime;

/**
 * Methods from this class serve as 'macros' to access attributes in instance or class
 * All parameters are strings (well-formatted identifiers/method names/etc.)
 * @author yi
 *
 */
public abstract class Code {
    
    /**
     * get class struct from the object instance
     * @param instance rjava object instance
     * @return
     */
    public static String getClassStructFromInstance(String instance) {
        return "((" + CLanguageRuntime.COMMON_INSTANCE_STRUCT + "*)" + instance + ") -> " + CLanguageRuntime.POINTER_TO_CLASS_STRUCT;
    }
    
    public static String getClassStruct(String klass) {
        return klass + CLanguageRuntime.CLASS_STRUCT_INSTANCE_SUFFIX;
    }
    
    /**
     * get class attribute from RJavaCommonClass
     * @param klass
     * @param attr
     * @return
     */
    public static String getClassAttribute(String klass, String attr) {
        return "((" + CLanguageRuntime.COMMON_CLASS_STRUCT + "*)(&" + getClassStruct(klass) + "))->" + attr;
    }
    
    /**
     * get class attribute from 'fromKlass'
     * @param klass
     * @param fromKlass
     * @param attr
     * @return
     */
    public static String getClassAttribute(String klass, String fromKlass, String attr) {
        return "((" + fromKlass + CLanguageRuntime.CLASS_STRUCT_SUFFIX + "*)(&" + getClassStruct(klass) + "))->" + attr;
    }
    
    public static String getClassAttributeFromInstance(String instance, String klass, String attr) {
        StringBuilder ret = new StringBuilder();
        ret.append("(");
        ret.append("(" + klass + CLanguageRuntime.CLASS_STRUCT_SUFFIX + "*)");
        ret.append("(");
        ret.append(getClassStructFromInstance(instance));
        ret.append("))");
        ret.append("->" + attr);
        return ret.toString();
    }
    
    public static String getClassAttributeFromInstance(String instance, String attr) {
        StringBuilder ret = new StringBuilder();
        ret.append("(");
        ret.append("(" + CLanguageRuntime.COMMON_CLASS_STRUCT + "*)");
        ret.append("(");
        ret.append(getClassStructFromInstance(instance));
        ret.append("))");
        ret.append("->" + attr);
        return ret.toString();
    }
    
    public static String getInstanceAttribute(String instance, String klass, String attr) {
        return "((" + klass + "*)" + instance + ")->" + attr;
    }
    
    public static String mutexLockOnInstance(String instance) {
        return "pthread_mutex_lock(&(((" + CLanguageRuntime.COMMON_INSTANCE_STRUCT + "*) " + instance + ") -> " + CLanguageRuntime.INSTANCE_MUTEX + "))";
    }
    
    public static String mutexUnlockOnInstance(String instance) {
        return "pthread_mutex_unlock(&(((" + CLanguageRuntime.COMMON_INSTANCE_STRUCT + "*) " + instance + ") -> " + CLanguageRuntime.INSTANCE_MUTEX + "))";
    }
    
    public static String mutexLockOnClass(String klass) {
        return "pthread_mutex_lock(&(((" + CLanguageRuntime.COMMON_CLASS_STRUCT + "*)(&" + klass + CLanguageRuntime.CLASS_STRUCT_INSTANCE_SUFFIX + ")) -> " + CLanguageRuntime.CLASS_MUTEX + "))";
    }
    
    public static String mutexUnlockOnClass(String klass) {
        return "pthread_mutex_unlock(&(((" + CLanguageRuntime.COMMON_CLASS_STRUCT + "*)(&" + klass + CLanguageRuntime.CLASS_STRUCT_INSTANCE_SUFFIX + ")) -> " + CLanguageRuntime.CLASS_MUTEX + "))";
    }

    /**
     * #include <header>
     * @param header
     * @return
     */
    public static String includeStandardHeader(String header) {
        return "#include <" + header + ">";
    }

    /**
     * #include "header"
     * @param header
     * @return
     */
    public static String includeNonStandardHeader(String header) {
        return "#include \"" + header + "\"";
    }

    public static String commentln(String s) {
        return "/* " + s + " */" + CLanguageGenerator.NEWLINE;
    }

    public static String comment(String s) {
        return "//" + s;
    }

}
