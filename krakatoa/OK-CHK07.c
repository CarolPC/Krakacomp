#include <malloc.h>
#include <stdlib.h>
#include <stdio.h>

typedef int boolean;
#define TRUE 1
#define FALSE 0

typedef
  void (*Func)();

typedef
   struct _St_Person {
      Func *vt;
      char * _Person_course;
      int _Person_number;
      int _Person_age;
      char * _Person_name;
      } _class_Person

_class_Person *new_Person(void)

char * _Person_getCourse( _class_Person ) {
}

void _Person_setCourse( _class_Personchar * course ) {
}

int _Person_getNumber( _class_Person ) {
}

void _Person_setNumber( _class_Personint number ) {
}

void _Person_init( _class_Personchar * name, int age ) {
}

char * _Person_getName( _class_Person ) {
}

int _Person_getAge( _class_Person ) {
}

void _Person_print( _class_Person ) {
}

Func VTClass_Person[] = {
   ( void (*)() ) _getCourse
   ( void (*)() ) _setCourse
   ( void (*)() ) _getNumber
   ( void (*)() ) _setNumber
   ( void (*)() ) _init
   ( void (*)() ) _getName
   ( void (*)() ) _getAge
   ( void (*)() ) _print
   };

_class_Person *new_Person()
{
   _class_Person *t

   if ( (t = malloc(sizeof(_class_Person))) != NULL )
      t->vt = _class_Person;
   return t;
   }

typedef
      struct _St_Group {
         Func *vt;
         _Person _Group_first;
         _Person _Group_second;
         } _class_Group

_class_Group *new_Group(void)

void _Group_set( _class_Group_Person first, _Person second ) {
}

_Person _Group_getFirst( _class_Group ) {
}

_Person _Group_getSecond( _class_Group ) {
}

void _Group_print( _class_Group ) {
}

Func VTClass_Group[] = {
   ( void (*)() ) _set
   ( void (*)() ) _getFirst
   ( void (*)() ) _getSecond
   ( void (*)() ) _print
   };

_class_Group *new_Group()
{
   _class_Group *t

   if ( (t = malloc(sizeof(_class_Group))) != NULL )
      t->vt = _class_Group;
   return t;
   }

typedef
      struct _St_University {
         Func *vt;
         char * _University_name;
         int _University_numberOfStudents;
         char * _University_city;
         } _class_University

_class_University *new_University(void)

void _University_init( _class_Universitychar * name, char * city, int numberOfStudents ) {
}

void _University_print( _class_University ) {
}

Func VTClass_University[] = {
   ( void (*)() ) _init
   ( void (*)() ) _print
   };

_class_University *new_University()
{
   _class_University *t

   if ( (t = malloc(sizeof(_class_University))) != NULL )
      t->vt = _class_University;
   return t;
   }

typedef
      struct _St_Program {
         Func *vt;
         } _class_Program

_class_Program *new_Program(void)

void _Program_run( _class_Program ) {
}

Func VTClass_Program[] = {
   ( void (*)() ) _run
   };

_class_Program *new_Program()
{
   _class_Program *t

   if ( (t = malloc(sizeof(_class_Program))) != NULL )
      t->vt = _class_Program;
   return t;
   }

   int main() {      _class_program *program;
      program = new_Program()
      ( ( void (*)(_class_Program *) ) program->vt[0] )(program)
      return 0;
   }
