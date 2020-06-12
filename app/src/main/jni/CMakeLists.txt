cmake_minimum_required(VERSION 3.6.0)

add_library(keys
            SHARED
            keys.c)

# Include libraries
target_link_libraries(keys
                      android
                      log)