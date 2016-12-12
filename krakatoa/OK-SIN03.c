#include <malloc.h>
#include <stdlib.h>
#include <stdio.h>

typedef int boolean;
#define TRUE 1
#define FALSE 0

typedef
  void (*Func)();

typedef
   struct _St_Program {
      Func *vt;
      } _class_Program;

_class_Program *new_Program(void);

void _Program_run( _class_Program *this ) {
   int _i;
   boolean _b;
   _i = ((-(7)   ) + ((2   ) * ((3) + (5)   )   ));
   _i = ((-(7)   ) + (8));
   _b = (((((((!true   ) || (_i <= (0)   )   ) || (_i < (0)   )   ) || (_i >= (0)   )   ) || (_i > (0)   )   ) || (_i == (7)   )   ) || (_i != (3)   ));
}

Func VTClass_Program[] = {
   ( void (*)() ) _Program_run
};

_class_Program *new_Program()
{
   _class_Program *t;

   if ( (t = malloc(sizeof(_class_Program))) != NULL )
      t->vt = VTClass_Program;
   return t;
}

int main() {
   _class_program *program;
   program = new_Program()
   ( ( void (*)(_class_Program *) ) program->vt[0] )(program)
   return 0;
}
