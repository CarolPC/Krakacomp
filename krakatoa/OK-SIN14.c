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
      } _class_Point;

_class_Point *new_Point(void);

void _Point_set( _class_Point *this,int _x, int _y ) {
   this->_x = _x;
   this->_y = _y;
}

int _Point_getX( _class_Point *this ) {
   return this->_x;
}

int _Point_getY( _class_Point *this ) {
   return this->_y;
}

Func VTClass_Point[] = {
   ( void (*)() ) _Point_getX,
   ( void (*)() ) _Point_getY,
   ( void (*)() ) _Point_set
};

_class_Point *new_Point()
{
   _class_Point *t;

   if ( (t = malloc(sizeof(_class_Point))) != NULL )
      t->vt = VTClass_Point;
   return t;
}

typedef
   struct _St_Program {
      Func *vt;
      _Point _Program_p;
      } _class_Program;

_class_Program *new_Program(void);

void _Program_run( _class_Program *this ) {
   this->_p = new_Point();
   ( (void(*)(_class_Point *, int, int)) this->_p->vt[0](this->_p,0, 0);
}

_Point _Program_getPoint( _class_Program *this ) {
   return this->_p;
}

Func VTClass_Program[] = {
   ( void (*)() ) _Program_getPoint,
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
