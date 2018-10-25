# Spaceboost code challenge

Welcome to the Spaceboost code challenge. We would like to have a restful API in order to manage the creation of our entities and obtain some statistics. 

The challenge consists in a Java SpringBoot Application.

## Introduction

You're given the following entities.

- Campaign
    - id

- AdGroup
    - id
    - campaignId
    - clicks
    - conversions
    - cost

- Keyword
    - id
    - campaignId
    - adGroupId
    - clicks
    - conversions
    - cost

## Rules

Consider the following:

1. One campaign can have N adGroups.
2. One adGroup can have N keywords.
3. One campaign can have N keywords but at least one adGroup.
4. One adGroup belongs to a single campaign.
5. One keyword belongs to a single adGroup.

## Goals

First, we need to be able to: 

* Create and get a single campaign.
* Create and get a single adGroup.
* Create and get a single keyword.

Secondly, we'll need to obtain the following metrics.

* The most clicked keyword.
* The keyword with most conversions.
* The campaign with most cost but less conversions.

Note: create an endpoint for each goal 

## Example

We want to get the keyword with identifier 1. 
Assuming that it belongs to campaign id 2 and ad group id 3, then the endpoint would be:

```
/campaigns/2/adGroups/3/keywords/1
```

## Important

Project must compile and all classes must be tested.

Make sure your application runs with:

```
> $ mvn spring-boot:run
```

## What we will value
- Clean code
- Avoid bugs
- Good test coverage
- Object Orientation practices
- Adherence to SOLID principles
- Good performance
- Concurrent file management
- Data structures and algorithms used as well as their space and time complexity.


## Notes
- Take advantage of the provided default classes.
- You're not allowed to modify any of the provided entities JSON files (campaigns, adGroups, keywords).
- You're not allowed to use any kind of databases.

## How to submit
Create a zip file and send it to the email that had this project attached. Please make sure to include all your source
files and your git folders with it.
## 