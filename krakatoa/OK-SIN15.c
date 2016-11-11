#include <malloc.h>
#include <stdlib.h>
#include <stdio.h>

typedef int boolean;
#define TRUE 1
#define FALSE 0

typedef
  void (*Func)();

typedef
   struct _St_Test {
      Func *vt;
      } _class_Test

_class_Test *new_Test(void)

char * _Test_fakeClone( _class_Testchar * s ) {
}

Func VTClass_Test[] = {
   ( void (*)() ) _fakeClone
   };

_class_Test *new_Test()
{
   _class_Test *t

   if ( (t = malloc(sizeof(_class_Test))) != NULL )
      t->vt = _class_Test;
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
