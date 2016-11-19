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
      int _A_n;
      } _class_A

_class_A *new_A(void)

void _A_set( _class_A *thisint pn ) {
    = pn;
   }
int _A_get( _class_A *this ) {
   return ;
   }
Func VTClass_A[] = {
   ( void (*)() ) _A_set,
   ( void (*)() ) _A_get
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

void _B_set( _class_B *thisint pn ) {
   printf();
   ;
   }
Func VTClass_B[] = {
   ( void (*)() ) _B_set
   };

_class_B *new_B()
{
   _class_B *t;

   if ( (t = malloc(sizeof(_class_B))) != NULL )
      t->vt = _class_B;
   return t;
   }

typedef
   struct _St_Program {
      Func *vt;
      } _class_Program

_class_Program *new_Program(void)

_B _Program_m( _class_Program *this_A a ) {
   ;
   return new_B();
   }
_A _Program_p( _class_Program *thisint i ) {
   }
void _Program_run( _class_Program *this ) {
   _A _a;
   _B _b;
   a = new_A();
   b = new_B();
   a = b;
   ;
   a = ;
   b = ;
   a = ;
   }
Func VTClass_Program[] = {
   ( void (*)() ) _Program_m,
   ( void (*)() ) _Program_p,
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
