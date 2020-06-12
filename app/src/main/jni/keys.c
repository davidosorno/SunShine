#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_dog_sunshine_util_ApiKey_00024Companion_getApiKey(JNIEnv *env, jobject instance) {
    return (*env)->  NewStringUTF(env, "M2M4ODUwZGNlNzgzZDZjMjFmNTdmZDY1MGQ2ZjQ2YmQ=");
//    3c8850dce783d6c21f57fd650d6f46bd
//    http://api.openweathermap.org/data/2.5/weather?lat=37.3692620&lon=-121.8706610&units=metric&appid=3c8850dce783d6c21f57fd650d6f46bd
//    https://openweathermap.org/current
}