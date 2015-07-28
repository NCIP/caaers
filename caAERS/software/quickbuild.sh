#!/bin/bash
ant publish-all -Dskip.test=true
cd web
./go
date
