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
      int _A_i;
      } _class_A

_class_A *new_A(void)

void _A_put( _class_A *thisint x, int y, int ok ) {
   }
int _A_get( _class_A *this ) {
   return ;
   }
void _A_set( _class_A *thisint i ) {
    = i;
   }
Func VTClass_A[] = {
   ( void (*)() ) _A_get,
   ( void (*)() ) _A_put,
   ( void (*)() ) _A_set
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

void _B_put( _class_B *thisint a, int b, int c ) {
   }
Func VTClass_B[] = {
   ( void (*)() ) _A_get,
   ( void (*)() ) _B_put,
   ( void (*)() ) _A_set
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

void _Program_run( _class_Program *this ) {
   _B _b;
   b = new_B();
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
