# Ofuscar o máximo possível
-optimizationpasses 5
-dontpreverify
-overloadaggressively
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

# Remova logs para reduzir o tamanho do APK e aumentar a segurança
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** w(...);
    public static *** v(...);
    public static *** i(...);
    public static *** e(...);
}

# Remover todos os comentários
-dontnote **

# Manter apenas o que é necessário para o funcionamento
# Manter todas as Activities
-keep public class * extends android.app.Activity {
    public void *(...);
}

# Manter todas as Fragments
-keep public class * extends android.app.Fragment {
    public void *(...);
}

# Manter as classes que herdam de Fragment (suporte)
-keep public class * extends androidx.fragment.app.Fragment {
    public void *(...);
}

# Manter as classes usadas em Views personalizadas
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# Manter as classes serializáveis
-keep class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Ofuscar tudo o que não é explicitamente mantido
-keep class * {
    public static void main(java.lang.String[]);
}

# Renomear classes agressivamente
-repackageclasses ''

# Ofuscar as classes e métodos que não devem ser explicitamente mantidos
-allowaccessmodification

# Remover classes, métodos e campos não utilizados
-ignorewarnings

