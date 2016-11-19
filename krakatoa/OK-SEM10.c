#include <malloc.h>
#include <stdlib.h>
#include <stdio.h>

typedef int boolean;
#define TRUE 1
#define FALSE 0

typedef
  void (*Func)();

typedef
   struct _St_A {
      Func *vt;
      } _class_A

_class_A *new_A(void)

void _A_first( _class_A *thisint pn ) {
   }
Func VTClass_A[] = {
   ( void (*)() ) _A_first
   };

_class_A *new_A()
{
   _class_A *t;

   if ( (t = malloc(sizeof(_class_A))) != NULL )
      t->vt = _class_A;
   return t;
   }

typedef
   struct _St_B {
      Func *vt;
      } _class_B

_class_B *new_B(void)

void _B_second( _class_B *this ) {
   }
Func VTClass_B[] = {
   ( void (*)() ) _B_second
   };

_class_B *new_B()
{
   _class_B *t;

   if ( (t = malloc(sizeof(_class_B))) != NULL )
      t->vt = _class_B;
   return t;
   }

typedef
   struct _St_C {
      Func *vt;
      } _class_C

_class_C *new_C(void)

void _C_third( _class_C *this ) {
   }
Func VTClass_C[] = {
   ( void (*)() ) _C_third
   };

_class_C *new_C()
{
   _class_C *t;

   if ( (t = malloc(sizeof(_class_C))) != NULL )
      t->vt = _class_C;
   return t;
   }

typedef
   struct _St_Program {
      Func *vt;
      } _class_Program

_class_Program *new_Program(void)

void _Program_run( _class_Program *this ) {
   _A _a;
   _B _b;
   _C _c;
   a = new_A();
   b = new_B();
   c = new_C();
   ;
   ;
   ;
   ;
   ;
   ;
   a = b;
   a = c;
   b = c;
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
