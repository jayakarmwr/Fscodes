/*
<!--
Employee Management WebSocket Application with MongoDB

Objective:
----------
Your task is to develop a WebSocket-based Employee Management System using Node.js and MongoDB. 
The system should allow multiple clients to interact with a database to perform the following operations:
	1. Insert Employee Records (INSERT <name> <salary> <role> <department> <experience>)
	2. Retrieve Employee List (RETRIEVE)
	3. Retrieve Employee List who belongs to a department (RETRIEVE_BY_DEPT <department>)
	
The WebSocket server should be capable of handling multiple concurrent clients and persist employee data in MongoDB.


// MongoDB Employee Schema
const employeeSchema = new mongoose.Schema({
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number
});

Requirements:
-------------
Implement WebSocket Server
	The server should:
		-> Accept multiple client connections. (give a response as "Connected" )
		-> Process incoming commands from clients as discussed above.
		-> Log each received command on the console.
		-> Ensure proper error handling (e.g., invalid salary, missing name, etc.).
		
Expected Behavior
-----------------

============================================================================================
Client Command			                Server Response
============================================================================================
INSERT Alice 50000 Developer IT 5	    "Employee inserted successfully."
INSERT Bob 60000 Manager IT 5	        "Employee inserted successfully."

RETRIEVE				                "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
                                        "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"

RETRIEVE_BY_DEPT IT                     "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
                                        "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"


INVALID					                "Invalid command."
============================================================================================

Note: 
-> Your implementation must use MongoDB for data persistence.
-> The server should run on port 8080.
-> The system should allow multiple clients to connect.


EXAMPLE URL value=>   ws://10.11.xx.xx:8080

-->
<config>
    <url value=""></url>
</config>
*/
const WebSocket = require('ws');
const mongoose=require("mongoose");
const { deprecate } = require('util');
const mongoosesequence=require("mongoose-sequence")(mongoose);


mongoose.connect("mongodb://localhost:27017/fs").then(()=>
{
    console.log("mongodb connected");
}).catch((err)=>console.log("error occured ",err));


const wss = new WebSocket.Server({ port: 8000 });
console.log("WebSocket server is running on ws://localhost:8080");

const userSchema=new mongoose.Schema({
    
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number
});
userSchema.plugin(mongoosesequence,{inc_field:"ID"});
const User=mongoose.model("user",userSchema);



wss.on('connection', (ws) => {
    console.log("New client connected.");

    ws.on('message', async(message) => {
        const action=message[0];
        const data={name:message[1],
            salary:message[2],
            role:message[3],
            department:message[4],
            experience:message[5],
        };
        switch(action)
        {
            case "INSERT":
                const newuser=await User(data);
                await newuser.save();
                ws.send(`Employee inserted successfully. ID: ${newuser.id}`);
            case "RETRIEVE":
                const finduser=await User.find();
                ws.send(`ID: ${finduser.id}, Name: ${finduser.name}, Salary: ${finduser.salary}, Role: ${finduser.role}, Department: ${finduser.department}, Experience: ${finduser.experience}`);
            case "RETRIEVE_BY_DEPT IT":
                const findbyid=await User.findById({department:"IT"});
                findbyid.forEach(element => {
                    ws.send(`ID: ${element.id}, Name: ${element.name}, Salary: ${element.salary}, Role: ${element.role}, Department: ${element.department}, Experience: ${element.experience}`);
                    
                });
            default:
                ws.send("Invalid command.");


        }
    });

    ws.on('close', () => {
        console.log("Client disconnected.");
    });
});

function handleCommand(command) {
    const parts = command.split(" ");

    if (parts[0] === "INSERT" && parts.length === 3) {
        const name = parts[1];
        const salary = parseInt(parts[2], 10);

        if (isNaN(salary)) return "Invalid command.";

        employees.push({ id: idCounter++, name, salary });
        return "Employee inserted successfully.";
    } 
    
    else if (parts[0] === "RETRIEVE") {
        if (employees.length === 0) return "No employees found.";
        return employees.map(emp => `ID: ${emp.id}, Name: ${emp.name}, Salary: ${emp.salary}`).join("\n");
    }

    return "Invalid command.";
}