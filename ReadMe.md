##** API TESTING**##

#####This project is build using Maven,TestNg and RestAssured#####

###Test Cases###

* Test Case 1
    * Create an Enrollment Record with the information as given below:
        * Driver ID: 9785550165     
        * Phone Number: +19785550165
        * Monitoring Period Start: 5 days from the current date
        * Monitoring Duration: Indefinite
        * Vehicle: 2013 Ford Focus
     * Print the status code and status detail of the response.
* Test Case 2
    * Get the Driver Status of the driver with ID: driver_00001.
    * Print the contents of the response, but with the following changes:
        * LastOnlineTime, MonitoringPeriodStart and LastTripTime in the format: Year/Month/Day Hours:Minutes:Seconds.
        * BackgroundAppRefreshState, WiFiLocationServiceState and GPSLocationServiceState values as either: ON, OFF or UNKNOWN.
        
* Test Case 3
    * UnEnroll the driver ID created in Test Case 1. 
    * Print the status code and status detail of the response.   
    
* Test Case 4
    What other tests can you write to further prove the functionality of these endpoints is working as expected and not causing unexpected behavior?

###Steps To Execute The TestCases###

* Download the folder and extract file in a specific location.
* Start Command Prompt.
* Go to the directory of folder downloaded where POM file resides.
* Type mvn compile and press enter.
* Type mvn test and press enter.

###Pre-Requirements for Execution###
* Maven
* JDK

