from flask import Flask, jsonify, request
from flask_marshmallow import Marshmallow
from marshmallow import post_load
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.orm import DeclarativeBase, Mapped, mapped_column

app = Flask(__name__)

app.config["SQLALCHEMY_DATABASE_URI"] = "sqlite:///demo.sqlite"
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = True
db = SQLAlchemy(app)


class Base(DeclarativeBase):
    pass


class Person(db.Model):
    id: Mapped[int] = mapped_column(db.Integer, primary_key=True)
    name: Mapped[str] = mapped_column(db.String)
    surname: Mapped[str] = mapped_column(db.String)
    job: Mapped[str] = mapped_column(db.String)


ma = Marshmallow(app)


class PersonSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = Person

    @post_load
    def make_user(self, data, **kwargs):
        return Person(**data)


with app.app_context():
    db.drop_all()
    db.create_all()
    db.session.add(Person(name="ser", surname="serowy", job='it'))
    db.session.commit()


@app.route('/person')
def get_person_list():
    with app.app_context():
        person_list = db.session.execute(db.select(Person)).scalars()
        return jsonify(PersonSchema(many=True).dump(person_list))


@app.route('/person/<int:person_id>')
def get_person(person_id):
    with app.app_context():
        person = db.get_or_404(Person, person_id)
        return jsonify(PersonSchema().dump(person))


@app.route('/person/create', methods=['POST'])
def create_person():
    with app.app_context():
        db.session.add(PersonSchema().load(request.json))
        db.session.commit()
        return "Person added"


if __name__ == '__main__':
    app.run()
