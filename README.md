# Geometry Inspector

Ever wanted to analyze various geometric shapes? 

When was the last time you thought to yourself, "I wonder if those two rectangles are adjacent?" 

Do you sometimes find yourself in a situation where you need to know the points of intersection between two rectangles, but don't have the necessary tools?

Look no further!


## Description ##
GeometryInspector analyzes geometric shapes and determines various features of those shapes and how the input shapes relate to each other. 


## Requirements ##
* Java 8+
* [Maven](https://maven.apache.org/) (if you want to clone and execute the project)


## Features ##
Version 1.0 is an early release supporting a reduced feature set with a singular focus on comparing 2 rectangles.


### Rectangle Analsyis ###
Currently only rectangle-to-rectangle comparison is available


#### Rectangle Comparison ####
Given two rectangles as input, GeometryInspector will perform analysis of both rectangles and provide output with how the rectangles relate to each other. If the rectangles overlap each other, the response will include the points at which they intersect. Below are the currently supported relationships:
* Separated - rectangle `A` and rectangle `B` do not touch or overlap
* Contained - either rectangle `A` or rectangle `B` wholly contains the other within it's bounds
* Adjacency - rectangle `A` is adjacent to rectangle `B`, sharing a side but not overlapping. 
    *  Proper - one side of rectangle `A` exactly lines up with a side of rectangle `B`
    *  Partial - some line segment on a side of rectangle `A` exists as a set of points on some side of Rectangle `B`
    *  Sub-Line - one side of rectangle `A` is a line that exists as a set of points wholly contained on some other side of rectangle `B`
* Intersection - rectangle `A` and rectangle `B` have one or more intersecting lines. The points of intersection will be provided as a list of point objects


## Executing ##
GeometryInspector can be executed in 2 ways:
1. Download and execute the pre-built JAR provided [here](https://github.com/waymirec/geometry/blob/master/GeometryInspector-1.0.jar)
2. Clone the source and load it into the Java IDE of your choice


#### Download & Execute ####
* Download the provided [JAR](https://github.com/waymirec/geometry/blob/master/GeometryInspector-1.0.jar)
   * In a graphical system, double click on the downloaded JAR
   * From a command terminal, change directory to where the JAR resides and execute `java -jar GeometryInspector-<version>.jar` (e.g. java -jar GeometryInspector-1.0.jar)

#### Clone & Execute ####
* Clone this repository into a local directory
* Import the code into the Java IDE of your choice (e.g. [IntelliJ IDEA](https://www.jetbrains.com/idea/))
* Open a terminal into the directory where the repository was cloned
* Execute `mvnw spring-boot:run`


## Help ##
* API reference is available within GeometryInspector via [Swagger](https://swagger.io/)
    * http://localhost:8080/swagger-ui.html#
* Examples of how to make various calls to GeometryInspector are available within the provided [Postman](https://www.postman.com/) collection
    * [geometry.postman_collection.json](https://github.com/waymirec/geometry/blob/master/geometry.postman_collection.json)


##### Examples #####
*POST http://localhost:8080/geometry/rectangle/analyze*
```
{
  "first": { 
    "lowerLeftX": 1, 
    "lowerLeftY": 1, 
    "upperRightX": 5, 
    "upperRightY": 5 
  },
    "second": { 
    "lowerLeftX": 2, 
    "lowerLeftY": 2, 
    "upperRightX": 3, 
    "upperRightY": 3 
  }
}
```

*Example Outputs*
>```
>{
>    "state": "CONTAINED",
>    "intersections": []
>}
>```

>```
>{
>    "state": "INTERSECTED",
>    "intersections": [
>        {
>            "x": 2.0,
>            "y": 3.0
>        },
>        {
>            "x": 3.0,
>            "y": 2.0
>        }
>    ]
>}
>```

>```
>{
>    "state": "ADJACENT",
>    "subState": "SUB_LINE",
>    "intersections": []
>}
>```
