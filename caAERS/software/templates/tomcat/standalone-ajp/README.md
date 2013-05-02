INSTALLATION
============

Step 1: Disable ReverseDNS lookup
-------
 For tomcat : the supplied server.xml has it turned-off
 For Apache : 
         a) Edit $httpd/conf/httpd.conf
      
              HostnameLookups Off
         b) Also make sure that we do not have %h in Log formats in any of the apache log file configs. %h stands for hostname. 

Step 2: Remove Unwanted connectors
-------
 Ideally when we front Tomcat with Apache, we can comment off HTTP and HTTPS connectors in "server.xml"
 Please comment them. 

Step 3: Copy the supplied server.xml (after making necessary edits) to Tomcat. 
-------




What is modified in the supplied server.xml ?

1. Optimized the thread settings
2. Turned-off reverseDNS lookups
