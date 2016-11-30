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

void _A_m( _class_A *this ) {
    =    0;
    = 0;
    = ;
   }
int _A_m_returns_boolean( _class_A *this ) {
   return ;
   }
void _A_m_integer( _class_A *thisint n ) {
    = n;
   }
void _A_m_integer_boolean_String( _class_A *thisint n, int b, char * s ) {
    = n;
    = b;
    = s;
   printf();
   }
int _A_m_integer_returns_boolean( _class_A *thisint n ) {
   }
int _A_m_integer_boolean_String_return( _class_A *thisint n, int b, char * s ) {
    = s;
   }
Func VTClass_A[] = {
   ( void (*)() ) _A_m,
   ( void (*)() ) _A_m_integer,
   ( void (*)() ) _A_m_integer_boolean_String,
   ( void (*)() ) _A_m_integer_boolean_String_return,
   ( void (*)() ) _A_m_integer_returns_boolean,
   ( void (*)() ) _A_m_returns_boolean
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

Func VTClass_B[] = {
   ( void (*)() ) _A_m,
   ( void (*)() ) _A_m_integer,
   ( void (*)() ) _A_m_integer_boolean_String,
   ( void (*)() ) _A_m_integer_boolean_String_return,
   ( void (*)() ) _A_m_integer_returns_boolean,
   ( void (*)() ) _A_m_returns_boolean
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
      char * _C_name;
      int _C_letter;
      int _C_n;
      int _C_time;
      } _class_C

_class_C *new_C(void)

void _C_method( _class_C *this ) {
    = ;
    = 0;
    =    0;
    =    0;
   }
int _C_method_returns_boolean( _class_C *this ) {
   return ;
   }
void _C_method_integer( _class_C *thisint n ) {
   printf();
   }
void _C_method_integer_boolean_String( _class_C *thisint n, int b, char * name ) {
    = name;
   printf();
   }
int _C_method_integer_returns_boolean( _class_C *thisint n ) {
   return ( > n);
   }
int _C_method_integer_boolean_String_r( _class_C *thisint n, int b, char * name ) {
   printf();
    = name;
   return (( > n   ) && !(b &&    ) && ( >    0   ));
   }
Func VTClass_C[] = {
   ( void (*)() ) _C_method,
   ( void (*)() ) _C_method_integer,
   ( void (*)() ) _C_method_integer_boolean_String,
   ( void (*)() ) _C_method_integer_boolean_String_r,
   ( void (*)() ) _C_method_integer_returns_boolean,
   ( void (*)() ) _C_method_returns_boolean
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
   _B _b;
   _C _c;
   b = new_B();
   ;
   c = new_C();
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
