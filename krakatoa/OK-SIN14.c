#include <malloc.h>
#include <stdlib.h>
#include <stdio.h>

typedef int boolean;
#define TRUE 1
#define FALSE 0

typedef
  void (*Func)();

typedef
   struct _St_Point {
      Func *vt;
      int _Point_x;
      int _Point_y;
      } _class_Point

_class_Point *new_Point(void)

void _Point_set( _class_Point *thisint x, int y ) {
    = x;
    = y;
   }
int _Point_getX( _class_Point *this ) {
   return ;
   }
int _Point_getY( _class_Point *this ) {
   return ;
   }
Func VTClass_Point[] = {
   ( void (*)() ) _Point_set,
   ( void (*)() ) _Point_getX,
   ( void (*)() ) _Point_getY
   };

_class_Point *new_Point()
{
   _class_Point *t;

   if ( (t = malloc(sizeof(_class_Point))) != NULL )
      t->vt = _class_Point;
   return t;
   }

typedef
   struct _St_Program {
      Func *vt;
      _Point _Program_p;
      } _class_Program

_class_Program *new_Program(void)

void _Program_run( _class_Program *this ) {
    = new_Point();
   ;
   }
_Point _Program_getPoint( _class_Program *this ) {
   return ;
   }
Func VTClass_Program[] = {
   ( void (*)() ) _Program_run,
   ( void (*)() ) _Program_getPoint
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
