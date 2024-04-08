# Headstart for Docker

This guide is a starting point for getting to know the basics of docker.

## Project Structure

The application consists of a backend and frontend application.
For both applications, there is a Dockerfile with which you can build the Docker image for the frontend and backend, respectively.

## 1. Build the backend Docker image

To build the backend Java application, run the following command in the `backend`-directory.

```shell
./gradlew build
```

To build the Docker image, run:

```shell
docker build -t dps-backend-practice .
```

## 2. Start a backend Docker container

To start a container for the newly-built backend application, execute the following command:

```shell
docker run -p 3001:3001 dps-backend-practice
```

Now, you should be able to open [this URL](http://localhost:3001/hello) in your browser and see something similar to:

"Hello from the Backend!"


## 3. Build the frontend Docker image

A backend is nothing without a frontend. Run the following command similar to step 1 in your `frontend`-directory.

```shell
./gradlew build
```

```shell
docker build -t dps-frontend-practice .
```


## 4. Start a frontend Docker container

To start a container for the newly-built frontend application, run the following command (similar to step 2):

```shell
docker run -p 3000:3000 dps-frontend-practice
```

Now, you should be able to open [this URL](http://localhost:3000/) in your browser and see something similar to:
"Main Page!"

## 5. Connect frontend and backend

Now it's time to "connect the backend and frontend".
In general, a frontend calls an API from the backend to retrieve some information that will be displayed.
For that, the frontend container needs to have an address to send the request to - some kind of URL.

To compose multiple containers and enable communication between them, we can use `docker compose`.
For that, `docker compose` allows to assign arbitrary names to containers and use this name as a URL that the frontend can call.

Take a look into the `docker-compose.yml` to get more information how the containers "can talk to each other".

To start the whole setup, simply run the following command in the root directory of this repository:
```shell
docker compose up
```

## 6. See the magic

Now, you should be able to open [this URL](http://localhost:3000/backend) in your browser and see something similar to:

"Response from Backend: Hello from the Backend!".

### What is happening in this endpoint?

Your browser calls the frontend, which itself calls the backend (using the dynamic URL "http://backend:3000").
Then, the frontend returns this response from the backend.

Awesome! now we got a full-stack application running using Docker (compose).

## Publishing your Docker images
[Here](https://docs.docker.com/guides/walkthroughs/publish-your-image/) is a guide on how you can publish your built docker images such that other developers/machines can download and use this image.

## Multi-architecture builds

If you are (or someone in your team) is developing on an Apple Silicon chip (M1/M2/M3), you might run into problems when running an image on your Windows/Linux that has been built on a MacBook.
This is because the processor architectures differ between both and we need to build one image per architecture (amd64 and arm/v7).

For that, docker allows you to cross-compile a docker image on your machine for a different architecture.
For more information, see [here](https://docs.docker.com/build/building/multi-platform/#getting-started).

## Further image/container customization

Some techniques that might be interested for more advanced usage. 

### Build/Environment Variables

You can define variables for build time and during runtime to make your container more environment-independent.
An example could be to pass in the Backend URL as an environment variable.

[This guide](https://vsupalov.com/docker-arg-env-variable-guide/) might be of interest.

### Multi-stage build

For example, when building a statically compiled frontend application, we don't need a NodeJS environment in our Docker container, anymore.
Creating a docker image that just contains a webserver link nginx that statically serves the web app would be way more light-weight.

For that, multi-stage builds might be interesting.
[Here](https://medium.com/@mohamedbenkhemiswork576/how-to-dockerize-a-react-app-with-multi-stage-build-and-nginx-minimize-react-image-size-by-80-33a09ae20118)'s an example for a React app.

### Mount Points/Volumes

Similar to mounting disks in virtual machine, you can also mount volumes (i.e. directories) into containers.

[This guide](https://blog.logrocket.com/docker-volumes-vs-bind-mounts/) might help you.
