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
      int _A_b;
      char * _A_s;
      } _class_A

_class_A *new_A(void)

void _A_m( _class_A ) {
}

int _A_m_returns_boolean( _class_A ) {
}

void _A_m_integer( _class_Aint n ) {
}

void _A_m_integer_boolean_String( _class_Aint n, int b, char * s ) {
}

int _A_m_integer_returns_boolean( _class_Aint n ) {
}

int _A_m_integer_boolean_String_return( _class_Aint n, int b, char * s ) {
}

Func VTClass_A[] = {
   ( void (*)() ) _m
   ( void (*)() ) _m_returns_boolean
   ( void (*)() ) _m_integer
   ( void (*)() ) _m_integer_boolean_String
   ( void (*)() ) _m_integer_returns_boolean
   ( void (*)() ) _m_integer_boolean_String_return
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
         } _class_B

_class_B *new_B(void)

Func VTClass_B[] = {
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
         char * _C_name;
         int _C_letter;
         int _C_n;
         int _C_time;
         } _class_C

_class_C *new_C(void)

void _C_method( _class_C ) {
}

int _C_method_returns_boolean( _class_C ) {
}

void _C_method_integer( _class_Cint n ) {
}

void _C_method_integer_boolean_String( _class_Cint n, int b, char * name ) {
}

int _C_method_integer_returns_boolean( _class_Cint n ) {
}

int _C_method_integer_boolean_String_r( _class_Cint n, int b, char * name ) {
}

Func VTClass_C[] = {
   ( void (*)() ) _method
   ( void (*)() ) _method_returns_boolean
   ( void (*)() ) _method_integer
   ( void (*)() ) _method_integer_boolean_String
   ( void (*)() ) _method_integer_returns_boolean
   ( void (*)() ) _method_integer_boolean_String_r
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
