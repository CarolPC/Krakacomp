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
      int _A_k;
      } _class_A

_class_A *new_A(void)

int _A_get_A( _class_A *this ) {
   return ;
   }
void _A_init( _class_A *this ) {
    =    1;
   }
Func VTClass_A[] = {
   ( void (*)() ) _A_get_A,
   ( void (*)() ) _A_init
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
      int _B_k;
      } _class_B

_class_B *new_B(void)

int _B_get_B( _class_B *this ) {
   return ;
   }
void _B_init( _class_B *this ) {
   ;
    =    2;
   }
Func VTClass_B[] = {
   ( void (*)() ) _A_get_A,
   ( void (*)() ) _B_get_B,
   ( void (*)() ) _B_init
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
      int _C_k;
      } _class_C

_class_C *new_C(void)

int _C_get_C( _class_C *this ) {
   return ;
   }
void _C_init( _class_C *this ) {
   ;
    =    3;
   }
Func VTClass_C[] = {
   ( void (*)() ) _A_get_A,
   ( void (*)() ) _B_get_B,
   ( void (*)() ) _C_get_C,
   ( void (*)() ) _C_init
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
      int _D_k;
      } _class_D

_class_D *new_D(void)

int _D_get_D( _class_D *this ) {
   return ;
   }
void _D_init( _class_D *this ) {
   ;
    =    4;
   }
Func VTClass_D[] = {
   ( void (*)() ) _A_get_A,
   ( void (*)() ) _B_get_B,
   ( void (*)() ) _C_get_C,
   ( void (*)() ) _D_get_D,
   ( void (*)() ) _D_init
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
   _A _a;
   _B _b;
   _C _c;
   _D _d;
   printf();
   printf();
   printf();
   printf();
   d = new_D();
   ;
   printf();
   c = d;
   printf();
   b = c;
   printf();
   a = b;
   printf();
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
