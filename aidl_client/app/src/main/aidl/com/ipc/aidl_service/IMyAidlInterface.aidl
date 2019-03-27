// IMyAidlInterface.aidl
package com.ipc.aidl_service;

// Declare any non-default types here with import statements

import com.ipc.aidl_service.Person;
interface IMyAidlInterface {

    void addPerson(in Person person);

    List<Person> getPersonList();
}
