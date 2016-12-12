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
      } _class_Test;

_class_Test *new_Test(void);

char * _Test_fakeClone( _class_Test *this,char * _s ) {
   return _s;
}

Func VTClass_Test[] = {
   ( void (*)() ) _Test_fakeClone
};

_class_Test *new_Test()
{
   _class_Test *t;

   if ( (t = malloc(sizeof(_class_Test))) != NULL )
      t->vt = VTClass_Test;
   return t;
}

typedef
   struct _St_Program {
      Func *vt;
      } _class_Program;

_class_Program *new_Program(void);

void _Program_run( _class_Program *this ) {
   char * _s;
   _Test _t;
   _s = ("Ola !!!");
   printf("%s   ",_s);
   _t = new_Test();
   printf("%s   ",( (char *(*)(_class_Test *, char *)) _t->vt[0](_class_Test *) this,"Dolly Parton"));
   printf("%s   ","barra   \\");
   printf("%s   ","barra n  \n");
   printf("%s   ","barra a  \a");
   printf("%s   ","barra barra \\\\");
   printf("%s   ","barra no final  \ ");
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
