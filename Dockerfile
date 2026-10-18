# 1. Image Java 17 officielle et à jour (Eclipse Temurin)
FROM eclipse-temurin:17-jdk

# 2. Installation des bibliothèques nécessaires pour l'affichage graphique Swing
RUN apt-get update && apt-get install -y \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    && rm -rf /var/lib/apt/lists/*

# 3. Répertoire de travail
WORKDIR /app

# 4. Copie du fichier source
COPY MonAppBanque.java .

# 5. Compilation
RUN javac MonAppBanque.java

# 6. Lancement de l'application
CMD ["java", "MonAppBanque"]