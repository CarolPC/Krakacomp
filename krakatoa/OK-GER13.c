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

int _A_get( _class_A *this ) {
   return ;
   }
void _A_set( _class_A *thisint n ) {
    = n;
   }
void _A_m1( _class_A *this ) {
   printf();
   }
Func VTClass_A[] = {
   ( void (*)() ) _A_get,
   ( void (*)() ) _A_set,
   ( void (*)() ) _A_m1
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

void _B_m2( _class_B *this ) {
   }
Func VTClass_B[] = {
   ( void (*)() ) _B_m2
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

void _C_m1( _class_C *this ) {
   printf();
   }
void _C_teste( _class_C *this ) {
   ;
   }
Func VTClass_C[] = {
   ( void (*)() ) _C_m1,
   ( void (*)() ) _C_teste
   };

_class_C *new_C()
{
   _class_C *t;

   if ( (t = malloc(sizeof(_class_C))) != NULL )
      t->vt = _class_C;
   return t;
   }

typedef
   struct _St_D {
      Func *vt;
      } _class_D

_class_D *new_D(void)

void _D_m1( _class_D *this ) {
   printf();
   }
Func VTClass_D[] = {
   ( void (*)() ) _D_m1
   };

_class_D *new_D()
{
   _class_D *t;

   if ( (t = malloc(sizeof(_class_D))) != NULL )
      t->vt = _class_D;
   return t;
   }

typedef
   struct _St_Program {
      Func *vt;
      } _class_Program

_class_Program *new_Program(void)

void _Program_run( _class_Program *this ) {
   _D _d;
   printf();
   printf();
   printf();
   printf();
   d = new_D();
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
