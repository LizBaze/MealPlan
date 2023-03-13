import { User } from 'src/app/models/user';

export class Grocery {

  id: number;
  name: string;
  completed: boolean;
  user: User | null;

  constructor(
    id: number = 0,
    name: string = "",
    completed: boolean = false,
    user: User | null = null
  ) {
    this.id = id;
    this.name = name;
    this.completed = completed;
    this.user = user;
  }
}
