const WebSocket = require('ws');

const server = new WebSocket.Server({ port: 8000 });
let clients=new Set();
server.on('connection',(ws)=>
{
    console.log("new client connected");
    clients.add(ws);

    ws.on('message',(message)=>
    {
        console.log(`received message ${message}`);
        for(let client of clients)
        {
            if(client.readyState===WebSocket.OPEN)
            {
                client.send(message.toString());
            }
        }
    });

    ws.on('close',()=>
    {
        console.log("client disconnected");
        clients.delete(ws);
    });
});

console.log("websocket is running");