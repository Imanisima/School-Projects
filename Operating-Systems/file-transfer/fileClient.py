
#! /usr/bin/env python3

import socket, sys, re, os
sys.path.append("../lib")       # for params
import params
from framedSock import framedSend, framedReceive

switchesVarDefaults = (
    (('-s', '--server'), 'server', "127.0.0.1:50001"),
    (('-d', '--debug'), "debug", False), # boolean (set if present)
    (('-?', '--usage'), "usage", False), # boolean (set if present)
    )

progname = "fileClient"
paramMap = params.parseParams(switchesVarDefaults)

server, usage, debug  = paramMap["server"], paramMap["usage"], paramMap["debug"]

if usage:
    params.usage()

try:
    serverHost, serverPort = re.split(":", server)
    serverPort = int(serverPort)
except:
    print("Can't parse server:port from '%s'" % server)
    sys.exit(1)

s = None
for res in socket.getaddrinfo(serverHost, serverPort, socket.AF_UNSPEC, socket.SOCK_STREAM):
    af, socktype, proto, canonname, sa = res
    try:
        print("creating sock: af=%d, type=%d, proto=%d" % (af, socktype, proto))
        s = socket.socket(af, socktype, proto)
    except socket.error as msg:
        print(" error: %s" % msg)
        s = None
        continue
    try:
        print(" attempting to connect to %s" % repr(sa))
        s.connect(sa)
    except socket.error as msg:
        print(" error: %s" % msg)
        s.close()
        s = None
        continue
    break

if s is None:
    print('could not open socket')
    sys.exit(1)

# send file
try:
    file = "test.txt" # userInput

    if os.path.isfile(file) == False:
        print("File not found. Terminating.")
        sys.exit(1)

    print("Sending file...")

    #s.send(file.encode())

except FileNotFoundError:
    print("File not found.")

print("Now opening file...")
data = s.recv(1024).decode()
print("\n %s \n" % data)
print("Received:", framedReceive(s, debug))

while 1:
    data = s.recv(1024).decode()
    print("Received '%s'" % data)
    print("Received:", framedReceive(s, debug))

    f = open(file, 'rb')
    readLine = f.read(1024)
    while(readLine):
        framedSend(s, readLine, debug)
        print("Sending ", readLine)
        readLine = f.read(1024)

    if len(data) == 0:
        print("Zero length read.")
        break
        
s.close()
print("Closing connection.")
