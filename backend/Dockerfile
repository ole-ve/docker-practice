# Define the base image (includes Linux distro with JVM installation)
FROM openjdk:17

# Set the working directory (like changing the directory in the console)
WORKDIR /app

# Copy the compiled jar-application into the container
COPY build/libs/backend-1.0.jar ./backend.jar

# Expose the port that we can access the container at
EXPOSE 3000

# Define the command that should be run when running the container
ENTRYPOINT ["java", "-jar", "./backend.jar"]
