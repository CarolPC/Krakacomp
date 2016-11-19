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

char * _Person_getCourse( _class_Person *this ) {
   return ;
   }
void _Person_setCourse( _class_Person *thischar * course ) {
    = course;
   }
int _Person_getNumber( _class_Person *this ) {
   return ;
   }
void _Person_setNumber( _class_Person *thisint number ) {
    = number;
   }
void _Person_init( _class_Person *thischar * name, int age ) {
    = name;
    = age;
   }
char * _Person_getName( _class_Person *this ) {
   return ;
   }
int _Person_getAge( _class_Person *this ) {
   return ;
   }
void _Person_print( _class_Person *this ) {
   printf();
   printf();
   }
Func VTClass_Person[] = {
   ( void (*)() ) _Person_getCourse,
   ( void (*)() ) _Person_setCourse,
   ( void (*)() ) _Person_getNumber,
   ( void (*)() ) _Person_setNumber,
   ( void (*)() ) _Person_init,
   ( void (*)() ) _Person_getName,
   ( void (*)() ) _Person_getAge,
   ( void (*)() ) _Person_print
   };

_class_Person *new_Person()
{
   _class_Person *t;

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

void _Group_set( _class_Group *this_Person first, _Person second ) {
    = first;
    = second;
   }
_Person _Group_getFirst( _class_Group *this ) {
   return ;
   }
_Person _Group_getSecond( _class_Group *this ) {
   return ;
   }
void _Group_print( _class_Group *this ) {
   printf();
   printf();
   }
Func VTClass_Group[] = {
   ( void (*)() ) _Group_set,
   ( void (*)() ) _Group_getFirst,
   ( void (*)() ) _Group_getSecond,
   ( void (*)() ) _Group_print
   };

_class_Group *new_Group()
{
   _class_Group *t;

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

void _University_init( _class_University *thischar * name, char * city, int numberOfStudents ) {
    = name;
    = city;
    = numberOfStudents;
   }
void _University_print( _class_University *this ) {
   printf();
   printf();
   printf();
   }
Func VTClass_University[] = {
   ( void (*)() ) _University_init,
   ( void (*)() ) _University_print
   };

_class_University *new_University()
{
   _class_University *t;

   if ( (t = malloc(sizeof(_class_University))) != NULL )
      t->vt = _class_University;
   return t;
   }

typedef
   struct _St_Program {
      Func *vt;
      } _class_Program

_class_Program *new_Program(void)

void _Program_run( _class_Program *this ) {
   _University _s;
   _Person _joao;
   _Person _maria;
   _Group _g;
   s = new_University();
   ;
   ;
   joao = new_Person();
   ;
   ;
   ;
   maria = new_Person();
   ;
   ;
   ;
   ;
   ;
   g = new_Group();
   ;
   ;
   }
Func VTClass_Program[] = {
   ( void (*)() ) _Program_run
   };

_class_Program *new_Program()
{
   _class_Program *t;

   if ( (t = malloc(sizeof(_class_Program))) != NULL )
      t->vt = _class_Program;
   return t;
   }

   int main() {      _class_program *program;
      program = new_Program()
      ( ( void (*)(_class_Program *) ) program->vt[0] )(program)
      return 0;
   }
