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
* [Docker](https://www.docker.com/) (if you want to use the included docker container)


## Features ##
Version 1.0 is an early release supporting a reduced feature set with a singular focus on comparing 2 rectangles.


### Rectangle Analsyis ###
Currently only rectangle-to-rectangle comparison is available. See the Appendix below.


#### Rectangle Comparison ####
Given two rectangles as input, GeometryInspector will perform analysis of both rectangles and provide output with how the rectangles relate to each other. If the rectangles overlap each other, the response will include the points at which they intersect. Below are the currently supported relationships:
* Intersection - rectangle `A` and rectangle `B` have one or more intersecting lines. The points of intersection will be provided as a list of point objects (_refer to appendix 1_)
* Contained - either rectangle `A` or rectangle `B` wholly contains the other within it's bounds (_refer to appendix 2_)
* Adjacency - rectangle `A` is adjacent to rectangle `B`, sharing a side but not overlapping. (_refer to appendix 3_)
    *  Proper - one side of rectangle `A` exactly lines up with a side of rectangle `B`
    *  Partial - some line segment on a side of rectangle `A` exists as a set of points on some side of Rectangle `B`
    *  Sub-Line - one side of rectangle `A` is a line that exists as a set of points wholly contained on some other side of rectangle `B`
* Separated - rectangle `A` and rectangle `B` do not touch or overlap


## Executing ##
GeometryInspector can be executed in 2 ways:
1. Run the provided docker container
2. Download and execute the pre-built JAR provided [here](https://github.com/waymirec/geometry/blob/master/GeometryInspector-1.0.jar)

##### Docker #####
* Clone this repository into a local directory
* Open a terminal into the directory where the repository was cloned
* Execute `inspector.sh`


##### Pre-Built JAR #####
* Download the provided [JAR](https://github.com/waymirec/geometry/blob/master/GeometryInspector-1.0.jar)
   * In a graphical system, double click on the downloaded JAR
   * From a command terminal, change directory to where the JAR resides and execute `java -jar GeometryInspector-<version>.jar` (e.g. java -jar GeometryInspector-1.0.jar)


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
>    "state": "SEPARATED",
>    "intersections": []
>}
>```

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

## Appendix ##
<img width="816" alt="Screen Shot 2021-03-25 at 5 19 33 PM" src="https://user-images.githubusercontent.com/1775806/112559580-7bfd4380-8d8e-11eb-884b-fa20497cad84.png">

<img width="816" alt="Screen Shot 2021-03-25 at 5 19 54 PM" src="https://user-images.githubusercontent.com/1775806/112559579-7bfd4380-8d8e-11eb-9fd0-e3ddc3b130ff.png">

<img width="816" alt="Screen Shot 2021-03-25 at 5 20 09 PM" src="https://user-images.githubusercontent.com/1775806/112559577-7acc1680-8d8e-11eb-9ff0-2e28e3a6ad77.png">
