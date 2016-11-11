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
      int _A_j;
      } _class_A

_class_A *new_A(void)

void _A_init_A( _class_A ) {
}

void _A_call_p( _class_A ) {
}

void _A_call_q( _class_A ) {
}

void _A_r( _class_A ) {
}

void _A_s( _class_A ) {
}

void _A_p( _class_A ) {
}

void _A_q( _class_A ) {
}

Func VTClass_A[] = {
   ( void (*)() ) _init_A
   ( void (*)() ) _call_p
   ( void (*)() ) _call_q
   ( void (*)() ) _r
   ( void (*)() ) _s
   };

_class_A *new_A()
{
   _class_A *t

   if ( (t = malloc(sizeof(_class_A))) != NULL )
      t->vt = _class_A;
   return t;
   }

typedef
      struct _St_B {
         Func *vt;
         int _B_i;
         int _B_j;
         } _class_B

_class_B *new_B(void)

void _B_init_B( _class_B ) {
}

void _B_call_p( _class_B ) {
}

void _B_call_q( _class_B ) {
}

void _B_r( _class_B ) {
}

void _B_s( _class_B ) {
}

void _B_p( _class_B ) {
}

void _B_q( _class_B ) {
}

Func VTClass_B[] = {
   ( void (*)() ) _init_B
   ( void (*)() ) _call_p
   ( void (*)() ) _call_q
   ( void (*)() ) _r
   ( void (*)() ) _s
   };

_class_B *new_B()
{
   _class_B *t

   if ( (t = malloc(sizeof(_class_B))) != NULL )
      t->vt = _class_B;
   return t;
   }

typedef
      struct _St_C {
         Func *vt;
         int _C_i;
         int _C_j;
         } _class_C

_class_C *new_C(void)

void _C_init_C( _class_C ) {
}

void _C_call_p( _class_C ) {
}

void _C_call_q( _class_C ) {
}

void _C_r( _class_C ) {
}

void _C_s( _class_C ) {
}

void _C_p( _class_C ) {
}

void _C_q( _class_C ) {
}

Func VTClass_C[] = {
   ( void (*)() ) _init_C
   ( void (*)() ) _call_p
   ( void (*)() ) _call_q
   ( void (*)() ) _r
   ( void (*)() ) _s
   };

_class_C *new_C()
{
   _class_C *t

   if ( (t = malloc(sizeof(_class_C))) != NULL )
      t->vt = _class_C;
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
