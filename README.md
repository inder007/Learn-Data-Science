# Learn Data Science

A sample website to learn about data science related libraries in python like numpy, torch, etc.
The code submitted by the user runs in docker container before [this commit](https://github.com/inder007/Learn-Data-Science/commit/4ef7947d72e33db3267af67954a8f12fc60a7206).
After this commit user code runs directly in the server without a separate container.  

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The backend web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Reactjs](https://reactjs.org/) - JavaScript library for building user interfaces.
* [Mongodb](https://docs.mongodb.com/) - Database used
* [Docker](https://docs.docker.com/) - For executing user code and deployments

## Getting started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
If you want to manage the website in local environment, you need to have following installed:

1. Java >= 8
2. Maven 
3. Node >= 14 
4. Mongo
5. Docker
6. Python3 (If running after [this commit](https://github.com/inder007/Learn-Data-Science/commit/4ef7947d72e33db3267af67954a8f12fc60a7206))

### Installing

Assuming you have all the above prerequisites up and running, follow these steps to run your app in local environment.


Fork or download this repo, to have your own local copy of this app to customize.
A "fork" is a copy of repository. So select fork atop [inder007/Learn-Data-Science repository](https://github.com/inder007/Learn-Data-Science/tree/master)
Or download the repository from clone or download tab.

Now, first we need to build the node project.

### Running/Building React Server

Go to the repository having react files.

```
cd src/main/resources/assets/
```

First install all the libraries provided in package.json file by running following command:

```
npm install
```

Now, to build the project run following:

```
npm run build
```

If you want to run development server, run the following command. 
This will run the development server on [localhost:5050](localhost:5050)
```
npm run dev
```

### Building dropwizard

Go back to root directory of this project by following command.

```
cd ../../../../
```

Build the project using following:
```
mvn package
```

Run the dropwizard server on [localhost:8080](localhost:8080)
```
java -jar target/LearnDataScience-1.0-SNAPSHOT.jar server configuration.yml
```

If you are running the code before [this commit](https://github.com/inder007/Learn-Data-Science/commit/4ef7947d72e33db3267af67954a8f12fc60a7206), 
you will require to create the image named sample. 

First go to the directory DockerContainer.
```
cd DockerContainer/
```

Now, build the image by following command. You may require sudo access to run following if you haven't added docker access to the current user.
```
docker build -t sample .
```

Now you have 2 servers running, one at [localhost:5050](http://localhost:5050) and other at [localhost:8080](localhost:8080)
You can run on port 5050 if you are changing only in reactjs app to see on the go changes.

## Building Docker Image

You can build the image by running following commands.
Please change the name of the database host accordingly. 
According to this docker-compose file, host should be set to mongodb (which is container name of mongodb set in docker-compose.yaml file).
```
docker-compose build
```

You can use following commands to run the containers. 
```
docker-compose up
```

## Deployments

You can use manifest files in the deployment folders to deploy the app on kubernetes cluster (say on GCP).
Assuming you have set up kubernetes cluster, you can run following to run your app on kubernetes cluster.

First set up the persistent volume(in my case was on GCP) using
```
kubectl apply -f googlecloud_ssd.yaml
```

Next, you will deploy mongodb on the cluster using
```
kubectl apply -f mongo.yaml
```

Lastly, we will deploy our app on cluster. 
Note that we have to provide the location of docker registry where our app is present. 
I have the app on inderchera/learn-python. Also note that for this pod to 
connect to mongodb pod, we have to give the host name in configuration.yaml file.
Currently I have hardcorded the host name before building the image, by
setting it to mongodb.default.svc.cluster.local. Replace mongodb with the serviceName 
in mongo.yaml file, and default with the namespace you are in.
```
kubectl apply -f dropwizard.yaml
```

Now, check the external ip address to access the app. Run following to get the ip address.
```
kubectl get svc
```

You will see the ip address under external-ip column